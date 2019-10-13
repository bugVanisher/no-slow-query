package com.noslowq.newsql.user.dao;

import com.noslowq.newsql.user.persistence.ddl.AppInfoDO;
import com.noslowq.newsql.user.persistence.ddl.AppInfoDOExample;
import com.noslowq.newsql.user.persistence.mapper.AppInfoDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class AppInfoDaoImpl {

    @Autowired
    private AppInfoDOMapper appInfoDOMapper;

    public List<AppInfoDO> getAppInfoByGroupIds(List<Long> groupIds) {
        if (null == groupIds || groupIds.size() == 0) {
            return Collections.emptyList();
        }
        AppInfoDOExample appInfoDOExample = new AppInfoDOExample();
        appInfoDOExample.createCriteria().andGroupIdIn(groupIds);
        return appInfoDOMapper.selectByExample(appInfoDOExample);
    }

    public AppInfoDO getAppInfo(Long appId) {
        return appInfoDOMapper.selectByPrimaryKey(appId);
    }
}
