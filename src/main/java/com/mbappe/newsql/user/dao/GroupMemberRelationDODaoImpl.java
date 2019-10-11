package com.mbappe.newsql.user.dao;

import com.mbappe.newsql.user.persistence.ddl.GroupMemberRelationDO;
import com.mbappe.newsql.user.persistence.mapper.GroupMemberRelationDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupMemberRelationDODaoImpl {

    @Autowired
    private GroupMemberRelationDOMapper groupMemberRelationDOMapper;

    public List<GroupMemberRelationDO> getRelationByUid(Long uid) {
        return null;
    }
}
