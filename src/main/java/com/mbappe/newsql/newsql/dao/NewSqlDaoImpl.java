package com.mbappe.newsql.newsql.dao;

/*  Created by heyu on 2019-10-04. 
    usage:     
*/

import com.mbappe.newsql.newsql.persistence.ddl.NewSqlDO;
import com.mbappe.newsql.newsql.persistence.ddl.NewSqlDOExample;
import com.mbappe.newsql.newsql.persistence.mapper.NewSqlDOMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NewSqlDaoImpl {

    @Autowired
    private NewSqlDOMapper newSqlDOMapper;

    public NewSqlDO getNewSqlByAppName(String appName, String sqlMd5, String dbName) {
        NewSqlDOExample example = new NewSqlDOExample();
        example.createCriteria().andAppNameEqualTo(appName).andSqlMd5EqualTo(sqlMd5).andDbNameEqualTo(dbName);
        List<NewSqlDO> templateSqlDOList = newSqlDOMapper.selectByExample(example);
        if (templateSqlDOList.size() > 0) {
            return templateSqlDOList.get(0);
        }
        return null;
    }
}
