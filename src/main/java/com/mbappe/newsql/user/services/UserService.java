package com.mbappe.newsql.user.services;


import com.mbappe.newsql.user.dao.AppInfoDaoImpl;
import com.mbappe.newsql.user.dao.GroupMemberRelationDODaoImpl;
import com.mbappe.newsql.user.dao.UserDoDaoImpl;
import com.mbappe.newsql.user.persistence.ddl.AppInfoDO;
import com.mbappe.newsql.user.persistence.ddl.GroupMemberRelationDO;
import com.mbappe.newsql.user.persistence.ddl.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDoDaoImpl userDoDao;

    @Autowired
    private GroupMemberRelationDODaoImpl groupMemberRelationDODao;

    @Autowired
    private AppInfoDaoImpl appInfoDao;

    public UserDO getByUsername(String username) {
        return userDoDao.findByUsername(username);
    }

    /**
     * 判断用户是否存在
     */
    private boolean exist(String username) {
        UserDO userDO = userDoDao.findByUsername(username);
        return (userDO != null);
    }

    public List<AppInfoDO> getAppInfosByUid(Long uid) {
        List<GroupMemberRelationDO> groupMemberRelationDOList = groupMemberRelationDODao.getRelationByUid(uid);
        List<Long> groupIds = new ArrayList<>();
        groupMemberRelationDOList.forEach(groupMemberRelationDO -> groupIds.add(groupMemberRelationDO.getGroupId())
                );
        return appInfoDao.getAppInfoByGroupIds(groupIds);
    }

    public boolean isAppAuth(Long appId) {
        return true;
    }
}
