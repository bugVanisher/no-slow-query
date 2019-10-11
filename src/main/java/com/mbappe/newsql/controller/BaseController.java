package com.mbappe.newsql.controller;

import com.mbappe.newsql.base.AjaxResponseBody;
import com.mbappe.newsql.user.persistence.ddl.UserDO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gannicus at 2019/2/20
 */
@Controller
@RestController
public class BaseController {

    public static UserDO currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            return (UserDO) auth.getPrincipal();
        } catch (ClassCastException e) {
            return null;
        }
    }

}
