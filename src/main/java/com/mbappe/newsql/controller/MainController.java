package com.mbappe.newsql.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

/**
 * @author gannicus at 2019/2/20
 */

@RestController
@RequestMapping(value = "/users")
public class MainController extends BaseController {

    /***
     * api :localhost:8099/users?id=99
     *  http://localhost:8099/users?limit=2&page=2
     * @param request
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> list(HttpServletRequest request) {
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
    }

//    @GetMapping("/hello")
    public String index() {
        return "hello world.";
    }


}