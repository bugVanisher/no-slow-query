package com.noslowq.newsql.base;

import com.noslowq.newsql.constants.StatusCode;

/**
 * ajax响应统一对象
 */
public class AjaxResponseBody<T> {
    //请求是否成功
    public boolean success = false;

    // 请求id
    public Long id;

    //返回状态码
    public int code;

    //附加信息
    public String message;
    public T data;

    public AjaxResponseBody(T data) {
        super();
        this.data = data;
    }

    public AjaxResponseBody(int code, String desc, T data) {
        super();
        this.data = data;
        this.code = code;
        this.message = desc;
    }

    public AjaxResponseBody() {
        super();
    }

    public static AjaxResponseBody forbidden() {
        return new AjaxResponseBody<>(StatusCode.FORBIDDEN_OPERATION.getCode(), StatusCode.FORBIDDEN_OPERATION.getDesc(), false);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean isSuccess) {
        this.success = isSuccess;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
