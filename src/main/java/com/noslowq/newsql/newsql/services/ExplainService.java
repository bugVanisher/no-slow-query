package com.noslowq.newsql.newsql.services;

import com.noslowq.newsql.constants.NoticeLevelEnum;
import com.noslowq.newsql.constants.ResultLevelsEnum;
import com.noslowq.newsql.dto.ExplainResult;
import com.noslowq.newsql.metaq.DbInfo;
import com.noslowq.newsql.metaq.SqlDto;
import com.noslowq.newsql.newsql.dao.ExplainDaoImpl;
import com.noslowq.newsql.newsql.persistence.ddl.ExplainDO;
import com.noslowq.newsql.utils.DBUtil;
import com.noslowq.newsql.utils.DateUtil;
import com.noslowq.newsql.utils.IdGenerator;
import com.noslowq.newsql.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*  Created by heyu on 2019-10-04.
    usage:
*/
@Service
public class ExplainService {

    private static final Logger logger = Logger.getLogger(ExplainService.class);

    @Autowired
    private ExplainDaoImpl explainDao;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private AnalyseService analyseService;

    public List<ExplainDO> getExplainResultById(Long id) {
        return explainDao.getExplainResultById(id);
    }

    /**
     * @param sqlDto
     * @param id     新增sql的主键id
     */
    public NoticeLevelEnum explain(SqlDto sqlDto, Long id) {
        String explainTempl = "explain %s;";
        DbInfo dbInfo = sqlDto.getDbInfo();
        List<ExplainResult> explainResults = null;
        try {
            Object obj = DBUtil.getList(dbInfo, ExplainResult.class, String.format(explainTempl, sqlDto.getSql()));
            explainResults = (List<ExplainResult>) obj;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("explain error:{},{}", sqlDto.getSql(), dbInfo);
        }
        if (null != explainResults && explainResults.size() > 0) {
            return addExplainResult(explainResults, sqlDto.getSql(), id);
        } else {
            try {
                addExplainRecord(sqlDto, id);
            } catch (Exception e) {
                logger.error(e, "addExplainRecord error.");
            }
        }
        return null;
    }

    private void addExplainRecord(SqlDto sqlDto, Long id) {
        ExplainDO explainResultEntity = new ExplainDO();
        explainResultEntity.setEid(idGenerator.genId());
        explainResultEntity.setId(id);
        explainResultEntity.setNewSql(sqlDto.getSql());
        explainResultEntity.setSlevel(ResultLevelsEnum.UNKNOWN.getCode().toString());
        explainResultEntity.setCtime(DateUtil.currentSecond());
        explainResultEntity.setUtime(DateUtil.currentSecond());
        explainDao.insert(explainResultEntity);
    }

    private NoticeLevelEnum addExplainResult(List<ExplainResult> explainResults, String rawSql, Long id) {
        NoticeLevelEnum noticeLevelEnum = NoticeLevelEnum.GOOD;
        for (ExplainResult explainResult : explainResults) {
            explainResult.setNew_sql(rawSql);
            ExplainDO explainResultEntity = new ExplainDO();
            explainResultEntity.setEid(idGenerator.genId());
            explainResultEntity.setId(id);
            explainResultEntity.setSelectType(explainResult.getSelect_type());
            explainResultEntity.setTablename(explainResult.getTable());
            explainResultEntity.setPartitions(explainResult.getPartitions());
            explainResultEntity.setType(explainResult.getType());
            explainResultEntity.setPossibleKeys(explainResult.getPossible_keys());
            explainResultEntity.setKey(explainResult.getKey());
            explainResultEntity.setKeyLen(explainResult.getKey_len());
            explainResultEntity.setRef(explainResult.getRef());
            explainResultEntity.setRows(explainResult.getRows());
            explainResultEntity.setFiltered(explainResult.getFiltered());
            explainResultEntity.setExtra(explainResult.getExtra());
            AnalyseService.AnalysisResult analysisResult = analyseService.analyse(explainResult);
            if (noticeLevelEnum.getCode() < analysisResult.finalEnum.getCode()) {
                noticeLevelEnum = analysisResult.finalEnum;
            }
            explainResultEntity.setSlevel(analysisResult.slevels);
            explainResultEntity.setNewSql(rawSql);
            explainResultEntity.setCtime(DateUtil.currentSecond());
            explainResultEntity.setUtime(DateUtil.currentSecond());
            explainDao.insert(explainResultEntity);

        }
        return noticeLevelEnum;
    }

    public List<ExplainDO> getExplainsByIds(List<Long> ids) {
        return explainDao.getExplainsByIds(ids);
    }
}
