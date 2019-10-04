package com.mbappe.newsql.newsql.services;

/*  Created by heyu on 2019-10-04. 
    usage:     
*/

import com.mbappe.newsql.newsql.dao.NewSqlDaoImpl;
import com.mbappe.newsql.newsql.dao.TemplateSqlDaoImpl;
import com.mbappe.newsql.newsql.persistence.ddl.NewSqlDO;
import com.mbappe.newsql.newsql.persistence.ddl.TemplateSqlDO;
import com.mbappe.newsql.utils.AlgorithmUtil;
import com.mbappe.newsql.utils.SqlParseUtil;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SqlService {

    @Autowired
    private AlgorithmUtil algorithmUtil;

    @Autowired
    private TemplateSqlDaoImpl templateSqlDao;

    @Autowired
    private NewSqlDaoImpl newSqlDao;

    public TemplateSqlDO getTemplateSqlByAppNameOriginalSql(String appName, String sql) {
        String sqlMd5 = algorithmUtil.getMd5(getParameterizedSql(sql));
        return templateSqlDao.getTemplateSqlByAppName(appName, sqlMd5);
    }

    public NewSqlDO getNewSqlByAppName(String appName, String orisql, String dbName) {
        String sqlMd5 = algorithmUtil.getMd5(orisql);
        return newSqlDao.getNewSqlByAppName(appName, sqlMd5, dbName);
    }

    private String getParameterizedSql(String sql) {
        if (StringUtil.isEmpty(sql)) {
            return null;
        }
        return SqlParseUtil.getUpCaseParameterizedSQL(sql);
    }

}
