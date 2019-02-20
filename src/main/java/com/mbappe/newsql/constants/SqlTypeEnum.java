package com.mbappe.newsql.constants;

/**
 *  SQL类型
 */
public enum SqlTypeEnum {

    TEMPLATE_SQL((short) 1, "参数化sql"),
    RAW_SQL((short) 2, "非参数化sql");

    private Short code;

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String desc;

    SqlTypeEnum(Short code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
