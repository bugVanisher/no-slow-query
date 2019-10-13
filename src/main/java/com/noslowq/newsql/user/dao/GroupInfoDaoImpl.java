package com.noslowq.newsql.user.dao;

import com.noslowq.newsql.user.persistence.ddl.GroupInfoDO;
import com.noslowq.newsql.user.persistence.mapper.GroupInfoDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupInfoDaoImpl {

    @Autowired
    private GroupInfoDOMapper groupInfoDOMapper;

    public List<GroupInfoDO> getGroupInfoByGroupIds(List<Long> gids) {
        return null;
    }
}
