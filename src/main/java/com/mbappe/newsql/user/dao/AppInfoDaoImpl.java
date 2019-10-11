package com.mbappe.newsql.user.dao;

import com.mbappe.newsql.user.persistence.ddl.AppInfoDO;
import com.mbappe.newsql.user.persistence.ddl.AppInfoDOExample;
import com.mbappe.newsql.user.persistence.mapper.AppInfoDOMapper;
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
}
