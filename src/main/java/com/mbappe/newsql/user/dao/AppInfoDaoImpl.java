package com.mbappe.newsql.user.dao;

import com.mbappe.newsql.user.persistence.ddl.AppInfoDO;
import com.mbappe.newsql.user.persistence.mapper.AppInfoDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppInfoDaoImpl {

    @Autowired
    private AppInfoDOMapper appInfoDOMapper;

    public List<AppInfoDO> getAppInfoByIds(List<Long> ids) {
        return null;
    }
}
