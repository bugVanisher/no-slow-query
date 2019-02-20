package com.mbappe.newsql.constants;

public enum NotifyRule {

    IMMEDIATE((short) 1, "立即推送"),
    CRON((short) 2, "定时推送");

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

    NotifyRule(Short code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
