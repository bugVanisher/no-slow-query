package com.noslowq.newsql.newsql.services;

/*  Created by heyu on 2019-10-04. 
    usage:     
*/

import com.noslowq.newsql.newsql.dao.SqlSourceDaoImpl;
import com.noslowq.newsql.newsql.persistence.ddl.SqlSourceDO;
import com.noslowq.newsql.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SqlSourceService {

    private static final Logger logger = Logger.getLogger(SqlSourceService.class);

    @Autowired
    private SqlSourceDaoImpl sqlSourceDao;

    public SqlSourceDO get(Long id) {
        return sqlSourceDao.get(id);
    }
}
