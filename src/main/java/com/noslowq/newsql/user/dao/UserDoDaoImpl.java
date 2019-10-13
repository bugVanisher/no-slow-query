package com.noslowq.newsql.user.dao;

import com.noslowq.newsql.user.persistence.ddl.UserDO;
import com.noslowq.newsql.user.persistence.ddl.UserDOExample;
import com.noslowq.newsql.user.persistence.mapper.UserDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDoDaoImpl {

    @Autowired
    private UserDOMapper userDOMapper;

    public UserDO findByUsername(String username) {
        UserDOExample userDOExample = new UserDOExample();
        userDOExample.createCriteria().andUsernameEqualTo(username);
        List<UserDO> userDOList = userDOMapper.selectByExample(userDOExample);
        if (null != userDOList && userDOList.size() > 0) {
            return userDOList.get(0);
        }
        return null;
    }

}
