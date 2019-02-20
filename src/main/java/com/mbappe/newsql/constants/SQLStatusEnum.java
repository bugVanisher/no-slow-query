package com.mbappe.newsql.constants;

/**
 * SQL状态枚举
 *
 * @author gannicus
 */
public enum SQLStatusEnum {
    INIT((short) 1, "未处理", "init"),
    IGNORED((short) 2, "已忽略", "ignored"),
    HANDLED((short) 3, "已处理", "handled"),
    CONFIRMING((short) 4, "待确认", "confirming"),
    MISTAKE((short) 5, "误报的", "mistaken"),
    FOLLOWING((short) 6, "跟进中", "following");

    private Short code;

    public Short getCode() {
        return code;
    }

    public void setCode(short code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String desc;

    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    SQLStatusEnum(Short code, String desc, String action) {
        this.code = code;
        this.desc = desc;
        this.action = action;
    }

    public static SQLStatusEnum getEnum(String action) {
        for (SQLStatusEnum handleStatusEnum : SQLStatusEnum.values()) {
            if (handleStatusEnum.getAction().equals(action)) {
                return handleStatusEnum;
            }
        }
        return null;
    }

    public static SQLStatusEnum getEnum(Short code) {
        for (SQLStatusEnum handleStatusEnum : SQLStatusEnum.values()) {
            if (handleStatusEnum.getCode().equals(code)) {
                return handleStatusEnum;
            }
        }
        return null;
    }
}

