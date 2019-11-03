package com.noslowq.newsql.newsql.services;

import com.noslowq.newsql.constants.ResultLevelsEnum;
import com.noslowq.newsql.constants.SQLStatusEnum;
import com.noslowq.newsql.dto.req.SearchCriteria;
import com.noslowq.newsql.dto.resp.SqlTag;
import com.noslowq.newsql.newsql.dao.NewSqlDaoImpl;
import com.noslowq.newsql.newsql.dao.TemplateSqlDaoImpl;
import com.noslowq.newsql.newsql.persistence.ddl.*;
import com.noslowq.newsql.user.dao.AppInfoDaoImpl;
import com.noslowq.newsql.user.persistence.ddl.AppInfoDO;
import com.noslowq.newsql.utils.AlgorithmUtil;
import com.noslowq.newsql.utils.DateUtil;
import com.noslowq.newsql.utils.Logger;
import com.noslowq.newsql.utils.SqlParseUtil;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/*  Created by heyu on 2019-10-04.
    usage:
*/

@Service
public class SqlService {

    private static final Logger logger = Logger.getLogger(SqlService.class);

    @Autowired
    private AlgorithmUtil algorithmUtil;

    @Autowired
    private TemplateSqlDaoImpl templateSqlDao;

    @Autowired
    private NewSqlDaoImpl newSqlDao;

    @Autowired
    private AppInfoDaoImpl appInfoDao;

    @Autowired
    private ExplainService explainService;

    public TemplateSqlDO getTemplateSqlByAppNameOriginalSql(String appName, String sql) {
        String sqlMd5 = algorithmUtil.getMd5(getParameterizedSql(sql));
        return templateSqlDao.getTemplateSqlByAppName(appName, sqlMd5);
    }

    public NewSqlDO getNewSqlByAppName(String appName, String orisql, String dbName) {
        String sqlMd5 = algorithmUtil.getMd5(orisql);
        return newSqlDao.getNewSqlByAppName(appName, sqlMd5, dbName);
    }

    public String getParameterizedSql(String sql) {
        if (StringUtil.isEmpty(sql)) {
            return null;
        }
        return SqlParseUtil.getUpCaseParameterizedSQL(sql);
    }

    public List<TemplateSqlDO> search(String appName, SearchCriteria searchCriteria) {
        TemplateSqlDOExample templateSqlDOExample = new TemplateSqlDOExample();
        TemplateSqlDOExample.Criteria cri = templateSqlDOExample.createCriteria();

        if (!StringUtil.isEmpty(appName)) {
            cri.andAppNameEqualTo(appName);
        }
        if (!StringUtil.isEmpty(searchCriteria.getTablename())) {
            cri.andTablenameEqualTo(searchCriteria.getTablename());
        }
        if (!StringUtil.isEmpty(searchCriteria.getLabel())) {
            cri.andLabelEqualTo(searchCriteria.getLabel());
        }
        if (null != searchCriteria.getSctime() && searchCriteria.getSctime() > 0) {
            cri.andCtimeGreaterThanOrEqualTo(searchCriteria.getSctime());
        }
        Integer sctime = searchCriteria.getSctime();
        Integer ectime = searchCriteria.getEctime();
        if (null == sctime) {
            sctime = DateUtil.currentSecond() - 15 * 24 * 3600;
        }
        if (null == ectime) {
            ectime = DateUtil.currentSecond();
        }
        List<Short> shortList = new ArrayList<>();
        shortList.add(SQLStatusEnum.INIT.getCode());
        shortList.add(SQLStatusEnum.FOLLOWING.getCode());
        shortList.add(SQLStatusEnum.CONFIRMING.getCode());
        cri.andHandleStatusIn(shortList);
        cri.andCtimeGreaterThanOrEqualTo(sctime);
        cri.andCtimeLessThanOrEqualTo(ectime);
        templateSqlDOExample.setOrderByClause("ctime desc");
        // // TODO: 2019/10/7 分页
        return templateSqlDao.getByExample(templateSqlDOExample);

    }

    public TemplateSqlDO getTemplateSql(Long id) {
        return templateSqlDao.getTemplateSqlById(id);
    }

    public List<String> getTableNamesByAppName(String appName) {
        return templateSqlDao.selectDistinctTableNames(appName);
    }

    public List<String> getLabelsByAppName(String appName) {
        return templateSqlDao.selectDistinctLabels(appName);
    }

    public AppInfoDO getAppInfoById(Long appId) {
        return appInfoDao.getAppInfo(appId);
    }

    public List<SqlTag> getSqlTagsByUid(Long uid) {
        List<ExplainDO> explainDOS = getExplainsByUid(uid);
        if (null == explainDOS) {
            return null;
        }
        Set<Integer> levels = new HashSet<>();
        explainDOS.forEach(explainDO -> {
            if (!StringUtil.isEmpty(explainDO.getSlevel())) {
                String[] singleLevels = explainDO.getSlevel().split(",");
                for (String le : singleLevels) {
                    Integer thisLevel = Integer.valueOf(le);
                    if (levels.contains(thisLevel)) {
                        continue;
                    }
                    levels.add(thisLevel);
                }
            }
        });
        List<SqlTag> sqlTags = new ArrayList<>();
        levels.forEach(level -> {
            SqlTag sqlTag = new SqlTag();
            ResultLevelsEnum resultLevelsEnum = ResultLevelsEnum.getResultLevelsEnum(level);
            if (null != resultLevelsEnum) {
                sqlTag.setCode(level);
                sqlTag.setName(resultLevelsEnum.getDesc());
                sqlTag.setType(resultLevelsEnum.getType());
                sqlTags.add(sqlTag);
            }
        });
        return sqlTags;
    }

    public List<ExplainDO> getExplainsByUid(Long uid) {
        List<Long> ids = newSqlDao.selectIdsByUid(uid);
        if (null == ids || ids.size() == 0) {
            return Collections.emptyList();
        }
        return explainService.getExplainsByIds(ids);
    }

    public List<NewSqlDO> getNewSqlByUid(Long uid) {
        return newSqlDao.getNewSqlByUid(uid);
    }
}
