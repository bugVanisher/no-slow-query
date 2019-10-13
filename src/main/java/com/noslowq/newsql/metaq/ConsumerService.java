package com.noslowq.newsql.metaq;

import com.noslowq.newsql.constants.NoticeLevelEnum;
import com.noslowq.newsql.constants.SQLStatusEnum;
import com.noslowq.newsql.constants.SqlTypeEnum;
import com.noslowq.newsql.dto.TableName;
import com.noslowq.newsql.newsql.dao.NewSqlDaoImpl;
import com.noslowq.newsql.newsql.dao.SqlSourceDaoImpl;
import com.noslowq.newsql.newsql.dao.TemplateSqlDaoImpl;
import com.noslowq.newsql.newsql.dto.TemplateSqlDOExt;
import com.noslowq.newsql.newsql.persistence.ddl.DbInfoDO;
import com.noslowq.newsql.newsql.persistence.ddl.NewSqlDO;
import com.noslowq.newsql.newsql.persistence.ddl.SqlSourceDO;
import com.noslowq.newsql.newsql.persistence.ddl.TemplateSqlDO;
import com.noslowq.newsql.newsql.services.DbInfoService;
import com.noslowq.newsql.newsql.services.ExplainService;
import com.noslowq.newsql.newsql.services.SqlService;
import com.mbappe.newsql.utils.*;
import com.noslowq.newsql.utils.*;
import jodd.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/*  Created by heyu on 2019-10-04.
    usage:     
*/
@Service
class ConsumerService {

    private static final Logger logger = Logger.getLogger(ConsumerService.class);

    private static final Integer MAX_COUNT = 10000 * 100;

    @Autowired
    private SqlService sqlService;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private DbInfoService dbInfoService;

    @Autowired
    private AlgorithmUtil algorithmUtil;

    @Autowired
    private TemplateSqlDaoImpl templateSqlDao;

    @Autowired
    private NewSqlDaoImpl newSqlDao;

    @Autowired
    private SqlSourceDaoImpl sqlSourceDao;

    @Autowired
    private ExplainService explainService;

    private TableName getTableName(String sql) {
        TableName tableName = new TableName();
        List<String> tables = SqlParseUtil.getTableNames(sql);
        List<String> uniqTables = SqlParseUtil.getTableNames(SqlParseUtil.getUpCaseParameterizedSQL(sql));
        if ((null == tables || null == uniqTables) || (tables.isEmpty() || uniqTables.isEmpty())) {
            tableName.setLogicTableName(SqlParseUtil.getTableName(sql));
            tableName.setRealTableName(SqlParseUtil.getTableName(sql));
            return tableName;
        }
        tableName.setRealTableName(tables.get(0).replaceAll("`", ""));
        tableName.setLogicTableName(uniqTables.get(0).replaceAll("`", ""));
        return tableName;

    }

    /**
     * 将sql进行格式化同时去掉注释（前注释）之类的内容
     */
    private void formatSqlDto(SqlDto sqlDto) {
        sqlDto.setOriginalSql(SqlParseUtil.getUpCaseKeywordsSQL(sqlDto.getOriginalSql()));
        sqlDto.setSql(SqlParseUtil.getUpCaseKeywordsSQL(sqlDto.getSql()));
    }

    void handle(SqlDto sqlDto) {
        Assert.notNull(sqlDto.getOriginalSql(), "sql is empty");
        Assert.notNull(sqlDto.getAppName(), "appname is empty");
        // not select query
        if (!SqlParseUtil.isSelectQuery(sqlDto.getOriginalSql())) {
            return;
        }
        TableName tableName = getTableName(sqlDto.getOriginalSql());
        if (StringUtil.isEmpty(tableName.getRealTableName()) || StringUtil.isEmpty(tableName.getLogicTableName())) {
            logger.error("no table name in this sql? : {}", sqlDto.getOriginalSql());
            return;
        }
        // sql格式化一遍再进行处理
        formatSqlDto(sqlDto);
        TemplateSqlDO templateSqlDO;
        NewSqlDO newSqlDO;
        DbInfoDO dbInfo = dbInfoService.getDbId(sqlDto.getDbInfo(), sqlDto.getAppName());
        templateSqlDO = sqlService.getTemplateSqlByAppNameOriginalSql(sqlDto.getAppName(), sqlDto.getOriginalSql());
        newSqlDO = sqlService.getNewSqlByAppName(sqlDto.getAppName(), sqlDto.getOriginalSql(), dbInfo.getMdb());
        // sql参数化后不存在则新增
        if (null == templateSqlDO) {
            logger.info("new template sql: {}, {}", sqlDto.getAppName(), sqlDto.getOriginalSql());
            TemplateSqlDO sqlEntity = new TemplateSqlDO();
            Long uniqId = idGenerator.genId();
            sqlEntity.setId(uniqId);
            sqlEntity.setAppName(sqlDto.getAppName());
            sqlEntity.setTablename(tableName.getLogicTableName());
            String us = sqlService.getParameterizedSql(sqlDto.getOriginalSql());
            sqlEntity.setTemplateSqlMd5(algorithmUtil.getMd5(us));
            sqlEntity.setTemplateSql(us);
            if (!StringUtil.isEmpty(sqlDto.getLabel())) {
                sqlEntity.setLabel(sqlDto.getLabel());
            } else {
                sqlEntity.setLabel("");
            }
            sqlEntity.setHandleStatus(SQLStatusEnum.INIT.getCode());
            if (sqlDto.getSql().equals(sqlDto.getOriginalSql())) {
                sqlEntity.setSqlType(SqlTypeEnum.RAW_SQL.getCode());
            } else {
                sqlEntity.setSqlType(SqlTypeEnum.TEMPLATE_SQL.getCode());
            }
            sqlEntity.setCtime(new Long(sqlDto.getCreatedTime() / 1000).intValue());
            sqlEntity.setUtime(DateUtil.currentSecond());
            sqlEntity.setNoticeLevel((short)0);
            // 再查一次减少并发问题
            templateSqlDO = sqlService.getTemplateSqlByAppNameOriginalSql(sqlDto.getAppName(), sqlDto.getOriginalSql());
            boolean succ = Boolean.FALSE;
            if (null == templateSqlDO) {
                try {
                    logger.info("find new template sql:{}", us);
                    succ = templateSqlDao.insert(sqlEntity);
                } catch (DuplicateKeyException e) {
                    logger.error("template sql duplicate:{}", sqlDto.getOriginalSql());
                }
            } else {
                logger.info("oops, template sql already exists: {}", sqlDto.getOriginalSql());
            }
            if (succ && null == newSqlDO) {
                TemplateSqlDOExt templateSqlEntity = new TemplateSqlDOExt();
                BeanUtils.copyProperties(sqlEntity, templateSqlEntity);
                templateSqlEntity.setShoudNotify(false);
                addNewSql(sqlDto, tableName.getRealTableName(), templateSqlEntity, dbInfo);
            }
        } else {
            // 存在了就判断原始sql是否是新增的
            if (null == newSqlDO) {
                TemplateSqlDOExt templateSqlEntity = new TemplateSqlDOExt();
                BeanUtils.copyProperties(templateSqlDO, templateSqlEntity);
                templateSqlEntity.setShoudNotify(false);
                addNewSql(sqlDto, tableName.getRealTableName(), templateSqlEntity, dbInfo);
            } else {
                // 次数太多不统计了
                if (newSqlDO.getNum() > MAX_COUNT) {
                    return;
                }
                newSqlDao.updateSqlNum(newSqlDO.getId(), newSqlDO.getNum() + 1);
            }
        }
    }

    private boolean addNewSql(SqlDto sqlDto, String tableName, TemplateSqlDOExt templateSqlDOExt, DbInfoDO dbInfoEntity) {
        logger.info("new sql: {}, {}, {}", sqlDto.getAppName(), dbInfoEntity.getMdb(), sqlDto.getOriginalSql());
        NewSqlDO newSqlInfoEntity = new NewSqlDO();
        Long id = idGenerator.genId();
        newSqlInfoEntity.setId(id);
        newSqlInfoEntity.setAppName(sqlDto.getAppName());
        newSqlInfoEntity.setUniqId(templateSqlDOExt.getId());
        newSqlInfoEntity.setTablename(tableName);
        newSqlInfoEntity.setDbId(dbInfoEntity.getId());
        newSqlInfoEntity.setDbName(dbInfoEntity.getMdb());
        newSqlInfoEntity.setSqlMd5(algorithmUtil.getMd5(sqlDto.getOriginalSql()));
        newSqlInfoEntity.setNewSql(sqlDto.getOriginalSql());
        newSqlInfoEntity.setNum(1);
        newSqlInfoEntity.setCtime(DateUtil.currentSecond());
        newSqlInfoEntity.setUtime(DateUtil.currentSecond());
        // 再查一次
        NewSqlDO entity = sqlService.getNewSqlByAppName(sqlDto.getAppName(), sqlDto.getOriginalSql(), dbInfoEntity.getMdb());
        if (null != entity) {
            logger.info("oops, new sql already exists: {}", sqlDto.getOriginalSql());
            return Boolean.FALSE;
        }
        boolean succ = Boolean.FALSE;
        try {
            logger.info("find new sql:{}", sqlDto.getOriginalSql());
            succ = newSqlDao.insert(newSqlInfoEntity);
        } catch (DuplicateKeyException e) {
            logger.error("newsql duplicate key,{}", sqlDto.getOriginalSql());
        }
        if (succ) {
            SqlSourceDO sqlSourceEntity = new SqlSourceDO();
            sqlSourceEntity.setId(id);
            sqlSourceEntity.setAppName(sqlDto.getAppName());
            sqlSourceEntity.setLabel(sqlDto.getLabel());
            sqlSourceEntity.setTrace(sqlDto.getTrace());
            sqlSourceEntity.setCtime(DateUtil.currentSecond());
            sqlSourceEntity.setUtime(DateUtil.currentSecond());
            sqlSourceDao.add(sqlSourceEntity);
            anlyseSql(sqlDto, id, templateSqlDOExt);
        }
        return succ;
    }

    /**
     * 分析sql,异步化
     *
     * @param sqlDto            sql消息
     * @param id                新增sqlID
     * @param templateSqlEntity sql实体
     */
    @Async
    void anlyseSql(SqlDto sqlDto, Long id, TemplateSqlDOExt templateSqlDOExt) {
        NoticeLevelEnum noticeLevelEnum = explainService.explain(sqlDto, id);
        updateFinal(templateSqlDOExt, noticeLevelEnum);
    }

    private void updateFinal(TemplateSqlDOExt templateSqlDOExt, NoticeLevelEnum noticeLevelEnum) {
        if (null != noticeLevelEnum) {
            boolean shouldUpdate = false;
            if (templateSqlDOExt.getNoticeLevel() == 0) {
                shouldUpdate = true;
            } else {
                if (templateSqlDOExt.getNoticeLevel() < noticeLevelEnum.getCode()) {
                    shouldUpdate = true;
                }
            }
            if (shouldUpdate) {
                TemplateSqlDO templateSqlDO = new TemplateSqlDO();
                templateSqlDO.setNoticeLevel(noticeLevelEnum.getCode().shortValue());
                templateSqlDao.updateById(templateSqlDOExt.getId(), templateSqlDO);
            }
            // bugfix 应该传ID重新查一遍,因为这里实体已经被更新过字段
//            if (templateSqlEntity.isShoudNotify()) {
//                notifyService.handle(templateSqlEntity.getId(), getSqlTagsByUid(templateSqlEntity.getId()));
//            }
        } else {
            // 没有分析结果,需要确认
            templateSqlDao.updateStatus(templateSqlDOExt.getId(), SQLStatusEnum.CONFIRMING.getCode(), SQLStatusEnum.INIT.getCode());
        }
    }

}
