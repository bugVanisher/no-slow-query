package com.mbappe.newsql.newsql.dao;

import com.mbappe.newsql.newsql.persistence.ddl.DbInfoDO;
import com.mbappe.newsql.newsql.persistence.mapper.DbInfoDOMapper;
import com.mysql.cj.x.json.DbDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author gannicus at 2019/2/20
 */
@Component
public class DbInfoDAOImpl {

    @Autowired
    private DbInfoDOMapper dbInfoMapper;


    public boolean insert(DbInfoDO dbInfoDO) {
        return dbInfoMapper.insert(dbInfoDO) > 0;
    }

    public DbInfoDO selectByPrimaryKey(Long dbId) {
        return dbInfoMapper.selectByPrimaryKey(dbId);
    }

}
