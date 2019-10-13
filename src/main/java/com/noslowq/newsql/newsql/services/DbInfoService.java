package com.noslowq.newsql.newsql.services;

import com.noslowq.newsql.constants.CachePrefix;
import com.noslowq.newsql.metaq.DbInfo;
import com.noslowq.newsql.newsql.dao.DbInfoDAOImpl;
import com.noslowq.newsql.newsql.persistence.ddl.DbInfoDO;
import com.noslowq.newsql.newsql.persistence.ddl.DbInfoDOExample;
import com.noslowq.newsql.newsql.persistence.mapper.DbInfoDOMapper;
import com.noslowq.newsql.utils.Cache;
import com.noslowq.newsql.utils.DateUtil;
import com.noslowq.newsql.utils.IdGenerator;
import com.noslowq.newsql.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gannicus at 2019/2/20
 */
@Service
public class DbInfoService {

    private static final Logger logger = Logger.getLogger(DbInfoService.class);


    @Autowired
    private DbInfoDAOImpl dbInfoDao;

    @Autowired
    private DbInfoDOMapper dbInfoDOMapper;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private Cache cache;

    private static Map<String, DbInfoDO> map = new ConcurrentHashMap<>();

    public DbInfoDO getDbId(DbInfo dbInfo, String appName) {
        List<DbInfoDO> dbInfoEntities;
        dbInfoEntities = cache.getList(appName, DbInfoDO.class, CachePrefix.APP_DBINFOS);
        if (null == dbInfoEntities) {
            DbInfoDOExample dbInfoEntityExample = new DbInfoDOExample();
            dbInfoEntityExample.createCriteria().andAppNameEqualTo(appName);
            dbInfoEntityExample.setOrderByClause("ctime asc");
            dbInfoEntities = dbInfoDOMapper.selectByExample(dbInfoEntityExample);
            cache.set(appName, dbInfoEntities, CachePrefix.APP_DBINFOS);
        }
        for (DbInfoDO dbInfoEntity : dbInfoEntities) {
            if (dbInfoEntity.getMhost().equals(dbInfo.getmHost())
                    && dbInfoEntity.getMport().equals(dbInfo.getmPort())
                    && dbInfoEntity.getMuser().equals(dbInfo.getmUserName())
                    && dbInfoEntity.getMdb().equals(dbInfo.getDb())) {
                return dbInfoEntity;
            }
        }
        return addDbInfo(dbInfo, appName);
    }

    private DbInfoDO addDbInfo(DbInfo dbInfo, String appName) {
        String dbInfoHash = String.valueOf(dbInfo.hashCode());
        if (map.containsKey(dbInfoHash)) {
            return map.get(dbInfoHash);
        }
        DbInfoDO dbInfoEntity = new DbInfoDO();
        dbInfoEntity.setId(idGenerator.genId());
        dbInfoEntity.setAppName(appName);
        dbInfoEntity.setMhost(dbInfo.getmHost());
        dbInfoEntity.setMport(dbInfo.getmPort());
        dbInfoEntity.setMuser(dbInfo.getmUserName());
        dbInfoEntity.setMpwd(dbInfo.getmPwd());
        dbInfoEntity.setMdb(dbInfo.getDb());
        dbInfoEntity.setCtime(DateUtil.currentSecond());
        logger.info("new dbinfo from:{}", dbInfoEntity.getAppName());
        if (dbInfoDao.insert(dbInfoEntity)) {
            map.put(dbInfoHash, dbInfoEntity);
            cache.delete(appName, CachePrefix.APP_DBINFOS);
            return dbInfoEntity;
        }
        return null;
    }

    public DbInfo getDbInfo(Long dbId) {
        DbInfoDO dbInfoEntity = dbInfoDao.selectByPrimaryKey(dbId);
        if (null == dbInfoEntity) {
            return null;
        }
        DbInfo dbInfo = new DbInfo();
        dbInfo.setmHost(dbInfoEntity.getMhost());
        dbInfo.setmPort(dbInfoEntity.getMport());
        dbInfo.setDb(dbInfoEntity.getMdb());
        dbInfo.setmUserName(dbInfoEntity.getMuser());
        dbInfo.setmPwd(dbInfoEntity.getMpwd());
        return dbInfo;
    }

}
