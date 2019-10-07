package com.mbappe.newsql.user.dao;

import com.mbappe.newsql.user.persistence.ddl.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mbappe.newsql.user.persistence.mapper.UserDOMapper;

@Component
public class UserDoDaoImpl {

    @Autowired
    private UserDOMapper userDOMapper;

    public UserDO findByUsername(String username) {
        return null;
    }

    /**
     * 判断用户是否存在
     */
    private boolean exist(String username) {
        return true;
    }
}
