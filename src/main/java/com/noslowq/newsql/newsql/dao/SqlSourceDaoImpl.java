package com.noslowq.newsql.newsql.dao;

/*  Created by heyu on 2019-10-06. 
    usage:     
*/

import com.noslowq.newsql.newsql.persistence.ddl.SqlSourceDO;
import com.noslowq.newsql.newsql.persistence.mapper.SqlSourceDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlSourceDaoImpl {

    @Autowired
    private SqlSourceDOMapper sqlSourceDOMapper;

    public boolean add(SqlSourceDO sqlSourceEntity) {
        return sqlSourceDOMapper.insert(sqlSourceEntity) > 0;
    }

    public SqlSourceDO get(Long id) {
        return sqlSourceDOMapper.selectByPrimaryKey(id);
    }
}
