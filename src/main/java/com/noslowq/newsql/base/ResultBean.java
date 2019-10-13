package com.noslowq.newsql.base;

import com.noslowq.newsql.constants.StatusCode;

import java.io.Serializable;

public class ResultBean<T> implements Serializable {

    //请求是否成功
    public boolean success = false;

    // 请求id
    public Long id;

    //返回状态码
    public int status;

    //附加信息
    public String message;

    private T data;


    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable throwable) {
        super();
        this.success = false;
        this.message = throwable.toString();
        this.status = StatusCode.INTERNAL_ERROR.getCode();
    }
}