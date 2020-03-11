package com.noslowq.newsql.controller;


import com.noslowq.newsql.base.AjaxResponseBody;
import com.noslowq.newsql.constants.SQLStatusEnum;
import com.noslowq.newsql.constants.StatusCode;
import com.noslowq.newsql.dto.req.Action;
import com.noslowq.newsql.dto.req.Handle;
import com.noslowq.newsql.dto.req.SearchCriteria;
import com.noslowq.newsql.dto.resp.SqlTag;
import com.noslowq.newsql.newsql.persistence.ddl.ExplainDO;
import com.noslowq.newsql.newsql.persistence.ddl.NewSqlDO;
import com.noslowq.newsql.newsql.persistence.ddl.OperationRecord;
import com.noslowq.newsql.newsql.persistence.ddl.TemplateSqlDO;
import com.noslowq.newsql.newsql.services.SqlService;
import com.noslowq.newsql.user.persistence.ddl.AppInfoDO;
import com.noslowq.newsql.user.persistence.ddl.UserDO;
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

    @GetMapping("/api/getTemplateSqlDetail")
    public AjaxResponseBody<TemplateSqlDO> getTemplateSqlDetail(@RequestParam(name = "uid") Long uid) {
        TemplateSqlDO templateSqlDO = sqlService.getTemplateSql(uid);
        AjaxResponseBody<TemplateSqlDO> result = new AjaxResponseBody<>(templateSqlDO);
        result.setSuccess(true);
        result.setCode(StatusCode.SUCCESS.getCode());
        return result;
    }

    @GetMapping("/api/getSqlLevelsByUid")
    public AjaxResponseBody<List<SqlTag>> getSqlLevelsById(@RequestParam(name = "uid") Long uid) {
        List<SqlTag> sqlTags = sqlService.getSqlTagsByUid(uid);
        AjaxResponseBody<List<SqlTag>> result = new AjaxResponseBody<>(sqlTags);
        result.setCode(StatusCode.SUCCESS.getCode());
        result.setSuccess(true);
        return result;
    }

    @GetMapping("/api/getNewSqlByUid")
    public AjaxResponseBody<List<NewSqlDO>> getNewSqlByUid(@RequestParam(name = "uid") Long uid) {
        List<NewSqlDO> newSqlDOList = sqlService.getNewSqlByUid(uid);
        AjaxResponseBody<List<NewSqlDO>> result = new AjaxResponseBody<>(newSqlDOList);
        result.setSuccess(true);
        result.setCode(StatusCode.SUCCESS.getCode());
        return result;
    }

    @GetMapping("/api/getExplainByUid")
    public AjaxResponseBody<List<ExplainDO>> getExplainByUid(@RequestParam(name = "uid") Long uid) {
        List<ExplainDO> explainDOS = sqlService.getExplainsByUid(uid);
        AjaxResponseBody<List<ExplainDO>> result = new AjaxResponseBody<>(explainDOS);
        result.setSuccess(true);
        result.setCode(StatusCode.SUCCESS.getCode());
        return result;
    }

    @PostMapping("/api/getNewsqlListByAppId")
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

    @PostMapping("/api/change")
    public AjaxResponseBody<Boolean> change(@RequestBody Action action) {
        boolean res;
        UserDO userDO = currentUser();
        if (null == userDO) {
            return new AjaxResponseBody<>(StatusCode.FORBIDDEN_OPERATION.getCode(), StatusCode.FORBIDDEN_OPERATION.getDesc(), null);
        }
        SQLStatusEnum toHandleStatusEnum = SQLStatusEnum.getEnum(action.getAction());
        if (SQLStatusEnum.IGNORED.equals(toHandleStatusEnum)) {
            res = sqlService.ignoreSql(action, userDO.getUsername());
        } else {
            res = sqlService.confirmSql(action, userDO.getUsername());
        }
        AjaxResponseBody<Boolean> result = new AjaxResponseBody<>(res);
        result.setSuccess(true);
        result.setCode(StatusCode.SUCCESS.getCode());
        return result;
    }

    @PostMapping("/api/handle")
    public AjaxResponseBody<Boolean> handle(@RequestBody Handle handle) {
        UserDO userDO = currentUser();
        if (null == userDO) {
            return new AjaxResponseBody<>(StatusCode.FORBIDDEN_OPERATION.getCode(), StatusCode.FORBIDDEN_OPERATION.getDesc(), null);
        }
        boolean res = sqlService.handleSql(handle, userDO.getUsername());
        AjaxResponseBody<Boolean> result = new AjaxResponseBody<>(res);
        result.setSuccess(true);
        result.setCode(StatusCode.SUCCESS.getCode());
        return result;
    }

    @GetMapping("/api/getOperationRecord")
    public AjaxResponseBody<List<OperationRecord>> getOperationRecord(@RequestParam(name="uid") Long uid) {
        List<OperationRecord> operationRecords = sqlService.getOpRecordByUid(uid);
        AjaxResponseBody<List<OperationRecord>> result = new AjaxResponseBody<>(operationRecords);
        result.setSuccess(true);
        result.setCode(StatusCode.SUCCESS.getCode());
        return result;
    }

}
