package com.mbappe.newsql.user.services;


import com.mbappe.newsql.user.dao.UserDoDaoImpl;
import com.mbappe.newsql.user.persistence.ddl.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDoDaoImpl userDoDao;

    public UserDO getByUsername(String username) {
        return userDoDao.findByUsername(username);
    }

    /**
     * 判断用户是否存在
     */
    private boolean exist(String username){
        UserDO userDO = userDoDao.findByUsername(username);
        return (userDO != null);
    }
}
