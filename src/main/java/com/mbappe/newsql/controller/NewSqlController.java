package com.mbappe.newsql.controller;


import com.mbappe.newsql.base.AjaxResponseBody;
import com.mbappe.newsql.constants.StatusCode;
import com.mbappe.newsql.dto.req.SearchCriteria;
import com.mbappe.newsql.newsql.persistence.ddl.TemplateSqlDO;
import com.mbappe.newsql.newsql.services.SqlService;
import com.mbappe.newsql.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/newsql")
public class NewSqlController extends BaseController {

    private static final Logger logger = Logger.getLogger(NewSqlController.class);

    @Autowired
    private SqlService sqlService;

    @PostMapping("/api/search")
    public AjaxResponseBody<List<TemplateSqlDO>> search(@RequestBody SearchCriteria search) {
        List<TemplateSqlDO> uniqSqlEntities = sqlService.search(search);
        AjaxResponseBody<List<TemplateSqlDO>> result = new AjaxResponseBody<>(uniqSqlEntities);
        result.setStatus(StatusCode.SUCCESS.getCode());
        result.setMessage(StatusCode.SUCCESS.getDesc());
        result.setSuccess(true);
        return result;
    }

}
