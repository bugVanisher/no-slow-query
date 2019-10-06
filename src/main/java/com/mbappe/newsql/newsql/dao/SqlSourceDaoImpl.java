package com.mbappe.newsql.newsql.dao;

/*  Created by heyu on 2019-10-06. 
    usage:     
*/

import com.mbappe.newsql.newsql.persistence.ddl.SqlSourceDO;
import com.mbappe.newsql.newsql.persistence.mapper.SqlSourceDOMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class SqlSourceDaoImpl {

    @Autowired
    private SqlSourceDOMapper sqlSourceDOMapper;

    public boolean add(SqlSourceDO sqlSourceEntity) {
        return sqlSourceDOMapper.insert(sqlSourceEntity) > 0;
    }
}
