package com.mbappe.newsql.constants;

public enum NotifyType {

    DINGDING((short) 1, "钉钉"),
    EMAIL((short) 2, "邮件"),
    MESSAGE((short) 3, "短信");

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

    NotifyType(Short code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static NotifyType getTypeByCode(Short code) {
        for (NotifyType notifyType : NotifyType.values()) {
            if (notifyType.getCode().equals(code)) {
                return notifyType;
            }
        }
        return null;
    }
}
