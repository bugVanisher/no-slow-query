package com.mbappe.newsql.newsql.dao;

/*  Created by heyu on 2019-10-04. 
    usage:     
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TemplateSqlDaoImpl {

    @Autowired
    private TemplateSqlDOMapper templateSqlMapper;

    public TemplateSqlDO getTemplateSqlByAppName(String appName, String sqlMd5) {
        TemplateSqlDOExample example = new TemplateSqlDOExample();
        example.createCriteria().andAppNameEqualTo(appName).andTemplateSqlMd5EqualTo(sqlMd5);
        List<TemplateSqlDO> templateSqlDOList = templateSqlMapper.selectByExample(example);
        if (null != templateSqlDOList && templateSqlDOList.size() > 0) {
            return templateSqlDOList.get(0);
        }
        return null;
    }

    public boolean insert(TemplateSqlDO templateSqlDO) {
        return templateSqlMapper.insert(templateSqlDO) > 0;
    }
}
