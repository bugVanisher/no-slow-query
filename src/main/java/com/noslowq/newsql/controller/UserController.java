package com.noslowq.newsql.controller;


import com.noslowq.newsql.base.AjaxResponseBody;
import com.noslowq.newsql.constants.StatusCode;
import com.noslowq.newsql.user.dto.UserInfo;
import com.noslowq.newsql.user.persistence.ddl.AppInfoDO;
import com.noslowq.newsql.user.persistence.ddl.UserDO;
import com.noslowq.newsql.user.services.UserService;
import com.noslowq.newsql.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/api/getApps")
    public AjaxResponseBody<List<AppInfoDO>> getTableNames() {
        UserDO userDO = currentUser();
        if (null == userDO) {
            return new AjaxResponseBody<>(StatusCode.FORBIDDEN_OPERATION.getCode(), StatusCode.FORBIDDEN_OPERATION.getDesc(), null);
        }
        List<AppInfoDO> appInfoDOList = userService.getAppInfosByUid(userDO.getId());
        AjaxResponseBody<List<AppInfoDO>> result = new AjaxResponseBody<>(appInfoDOList);
        result.setSuccess(true);
        result.setCode(StatusCode.SUCCESS.getCode());
        return result;
    }

    @GetMapping("/api/info")
    public AjaxResponseBody<UserInfo> getUser() {
        UserDO userDO = currentUser();
        if (null == userDO) {
            return new AjaxResponseBody<>(StatusCode.FORBIDDEN_OPERATION.getCode(), StatusCode.FORBIDDEN_OPERATION.getDesc(), null);
        }
        // TODO 用户信息暂时写死
        UserInfo userInfo = new UserInfo();
        userInfo.setName(userDO.getUsername());
        userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        userInfo.setRoles(roles);
        userInfo.setIntroduction("Nick name: " + userDO.getNickname());
        AjaxResponseBody<UserInfo> result = new AjaxResponseBody<>(userInfo);
        result.setSuccess(true);
        result.setCode(StatusCode.SUCCESS.getCode());
        return result;
    }

}
