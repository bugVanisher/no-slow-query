package com.noslowq.newsql.newsql.dao;

/*  Created by heyu on 2019-10-04. 
    usage:     
*/

import com.noslowq.newsql.newsql.persistence.ddl.NewSqlDO;
import com.noslowq.newsql.newsql.persistence.ddl.NewSqlDOExample;
import com.noslowq.newsql.newsql.persistence.mapper.NewSqlDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewSqlDaoImpl {

    @Autowired
    private NewSqlDOMapper newSqlDOMapper;

    public NewSqlDO getNewSqlByAppName(String appName, String sqlMd5, String dbName) {
        NewSqlDOExample example = new NewSqlDOExample();
        example.createCriteria().andAppNameEqualTo(appName).andSqlMd5EqualTo(sqlMd5).andDbNameEqualTo(dbName);
        List<NewSqlDO> newSqlDOList = newSqlDOMapper.selectByExample(example);
        if (null != newSqlDOList && newSqlDOList.size() > 0) {
            return newSqlDOList.get(0);
        }
        return null;
    }


    public boolean insert(NewSqlDO newSqlDO) {
        return newSqlDOMapper.insert(newSqlDO) > 0;
    }

    public boolean updateSqlNum(Long id, Integer num) {
        NewSqlDOExample example = new NewSqlDOExample();
        example.createCriteria().andIdEqualTo(id);
        NewSqlDO newSqlDO = new NewSqlDO();
        newSqlDO.setNum(num);
        return newSqlDOMapper.updateByExampleSelective(newSqlDO, example) > 0;
    }
}
