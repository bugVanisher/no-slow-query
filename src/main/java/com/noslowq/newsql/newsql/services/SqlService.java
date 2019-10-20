package com.noslowq.newsql.newsql.services;

import com.noslowq.newsql.constants.SQLStatusEnum;
import com.noslowq.newsql.dto.req.SearchCriteria;
import com.noslowq.newsql.newsql.dao.NewSqlDaoImpl;
import com.noslowq.newsql.newsql.dao.TemplateSqlDaoImpl;
import com.noslowq.newsql.newsql.persistence.ddl.NewSqlDO;
import com.noslowq.newsql.newsql.persistence.ddl.TemplateSqlDO;
import com.noslowq.newsql.newsql.persistence.ddl.TemplateSqlDOExample;
import com.noslowq.newsql.user.dao.AppInfoDaoImpl;
import com.noslowq.newsql.user.persistence.ddl.AppInfoDO;
import com.noslowq.newsql.utils.AlgorithmUtil;
import com.noslowq.newsql.utils.DateUtil;
import com.noslowq.newsql.utils.Logger;
import com.noslowq.newsql.utils.SqlParseUtil;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public TemplateSqlDO getUniqSqlById(Long id) {
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
}
