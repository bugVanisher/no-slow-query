package com.mbappe.newsql.newsql.dao;

import com.mbappe.newsql.newsql.ddl.DbInfoDO;
import com.mbappe.newsql.newsql.ddl.DbInfoParam;
import com.mbappe.newsql.newsql.mapper.DbInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author gannicus at 2019/2/20
 */
@Component
public class DbInfoDAOImpl {

    @Autowired
    private DbInfoMapper dbInfoMapper;

    public List<DbInfoDO> selectByParam(String appName) {
        DbInfoParam dbInfoParam = new DbInfoParam();
        dbInfoParam.createCriteria().andAppNameEqualTo(appName);
        dbInfoParam.appendOrderByClause(DbInfoParam.OrderCondition.CTIME, DbInfoParam.SortType.ASC);
        return dbInfoMapper.selectByParam(dbInfoParam);
    }

    public List<DbInfoDO> selectAll(String appName) {
        DbInfoParam dbInfoParam = new DbInfoParam();
        dbInfoParam.appendOrderByClause(DbInfoParam.OrderCondition.CTIME, DbInfoParam.SortType.ASC);
        return dbInfoMapper.selectByParam(dbInfoParam);
    }


}
