package com.mbappe.newsql.controller;


import com.mbappe.newsql.base.AjaxResponseBody;
import com.mbappe.newsql.constants.StatusCode;
import com.mbappe.newsql.dto.req.SearchCriteria;
import com.mbappe.newsql.newsql.persistence.ddl.TemplateSqlDO;
import com.mbappe.newsql.newsql.services.SqlService;
import com.mbappe.newsql.user.persistence.ddl.AppInfoDO;
import com.mbappe.newsql.utils.Logger;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/newsql")
public class NewSqlController extends BaseController {

    private static final Logger logger = Logger.getLogger(NewSqlController.class);

    @Autowired
    private SqlService sqlService;

    @PostMapping("/api/search")
    public AjaxResponseBody<List<TemplateSqlDO>> search(@RequestBody SearchCriteria search) {
        List<TemplateSqlDO> templateSqlDOList = sqlService.search(search);
        AjaxResponseBody<List<TemplateSqlDO>> result = new AjaxResponseBody<>(templateSqlDOList);
        result.setCode(StatusCode.SUCCESS.getCode());
        result.setMessage(StatusCode.SUCCESS.getDesc());
        result.setSuccess(true);
        return result;
    }

    @GetMapping("/api/getTablesByAppId")
    public AjaxResponseBody<List<String>> getTableNames(@RequestParam(name = "appId") Long appId) {
        AppInfoDO appInfoDO = sqlService.getAppInfoById(appId);
        if (null == appInfoDO) {
            return new AjaxResponseBody<>(null);
        }
        List<String> tableNames = sqlService.getTableNamesByAppName(appInfoDO.getAppName());
        AjaxResponseBody<List<String>> result = new AjaxResponseBody<>(tableNames);
        result.setSuccess(true);
        result.setCode(StatusCode.SUCCESS.getCode());
        return result;
    }

    @GetMapping("/api/getLabels")
    public AjaxResponseBody<List<String>> selectLabels(@RequestParam(name = "appName") String appName) {
//        List<String> envs = sqlService.getEnvsByAppName(appName);
        AjaxResponseBody<List<String>> result = new AjaxResponseBody<>(Collections.emptyList());
        result.setSuccess(true);
        return result;
    }

}
