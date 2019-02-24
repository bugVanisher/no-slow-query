package com.mbappe.newsql.controller;

import com.mbappe.newsql.newsql.services.DbInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author gannicus at 2019/2/20
 */

@RestController
@RequestMapping(value = "/db")
public class MainController extends BaseController {

    @Autowired
    private DbInfoService dbInfoService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> list(@RequestParam(name = "appName") String appName) {

        return new ResponseEntity<>(dbInfoService.get(appName), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String index() {
        return "hello world.";
    }


}