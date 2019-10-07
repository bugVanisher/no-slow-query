package com.mbappe.newsql.base;

/**
 *  ajax响应统一对象
 */
public class AjaxResponseBody<T> {
    //请求是否成功
    public boolean success = false;

    // 请求id
    public Long id;

    //返回状态码
    public int status;

    //附加信息
    public String message;
    public T data;

    public AjaxResponseBody(T data) {
        super();
        this.data = data;
    }

    public AjaxResponseBody() {
        super();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
