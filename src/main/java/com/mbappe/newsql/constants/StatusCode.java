package com.mbappe.newsql.constants;

public enum StatusCode {

    SUCCESS(20000, "ok"),
    /**
     * 服务端内部错误
     */
    INTERNAL_ERROR(50008, "未知错误"),
    /**
     * 应用不合法
     */
    APP_ERROR(50001, "未授权应用"),

    /**
     * 签名错误
     */
    SIGN_ERROR(50002, "签名错误"),
    /**
     * 请求参数不合法
     */
    ILLEGAL_PARAM(50003, "请求参数不合法");

    private Integer code;
    private String desc;

    private StatusCode(Integer code, String description) {
        this.code = code;
        this.desc = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
