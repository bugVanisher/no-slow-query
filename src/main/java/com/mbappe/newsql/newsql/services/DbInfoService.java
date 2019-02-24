package com.mbappe.newsql.newsql.services;

import com.mbappe.newsql.newsql.dao.DbInfoDAOImpl;
import com.mbappe.newsql.newsql.ddl.DbInfoDO;
import com.mbappe.newsql.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gannicus at 2019/2/20
 */
@Service
public class DbInfoService {

    private static final Logger logger = Logger.getLogger(DbInfoService.class);


    @Autowired
    private DbInfoDAOImpl dbInfoDao;

    public List<DbInfoDO> get(String appName) {
        return dbInfoDao.selectAll(appName);
    }

}
