package com.noslowq.newsql.controller;


import com.noslowq.newsql.base.AjaxResponseBody;
import com.noslowq.newsql.constants.StatusCode;
import com.noslowq.newsql.dto.req.SearchCriteria;
import com.noslowq.newsql.newsql.persistence.ddl.TemplateSqlDO;
import com.noslowq.newsql.newsql.services.SqlService;
import com.noslowq.newsql.user.persistence.ddl.AppInfoDO;
import com.noslowq.newsql.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/newsql")
public class NewSqlController extends BaseController {

    private static final Logger logger = Logger.getLogger(NewSqlController.class);

    @Autowired
    private SqlService sqlService;

    @GetMapping("/api/getTablesByAppId")
    public AjaxResponseBody<List<String>> getTableNames(@RequestParam(name = "appId") Long appId) {
        AppInfoDO appInfoDO = sqlService.getAppInfoById(appId);
        if (null == appInfoDO) {
            return new AjaxResponseBody<List<String>>().dataNotExist();
        }
        List<String> tableNames = sqlService.getTableNamesByAppName(appInfoDO.getAppName());
        AjaxResponseBody<List<String>> result = new AjaxResponseBody<>(tableNames);
        result.setSuccess(true);
        result.setCode(StatusCode.SUCCESS.getCode());
        return result;
    }


    @GetMapping("/api/getLabelsByAppId")
    public AjaxResponseBody<List<String>> getLabels(@RequestParam(name = "appId") Long appId) {
        AppInfoDO appInfoDO = sqlService.getAppInfoById(appId);
        if (null == appInfoDO) {
            return new AjaxResponseBody<List<String>>().dataNotExist();
        }
        List<String> labels = sqlService.getLabelsByAppName(appInfoDO.getAppName());
        AjaxResponseBody<List<String>> result = new AjaxResponseBody<>(labels);
        result.setSuccess(true);
        result.setCode(StatusCode.SUCCESS.getCode());
        return result;
    }

    @PostMapping("/api/getNewsqlByAppId")
    public AjaxResponseBody<List<TemplateSqlDO>> search(@RequestBody SearchCriteria search) {
        AppInfoDO appInfoDO = sqlService.getAppInfoById(search.getAppId());
        if (null == appInfoDO) {
            return new AjaxResponseBody<List<TemplateSqlDO>>().dataNotExist();
        }
        List<TemplateSqlDO> templateSqlDOList = sqlService.search(appInfoDO.getAppName(), search);
        AjaxResponseBody<List<TemplateSqlDO>> result = new AjaxResponseBody<>(templateSqlDOList);
        result.setSuccess(true);
        result.setCode(StatusCode.SUCCESS.getCode());
        return result;
    }

}
