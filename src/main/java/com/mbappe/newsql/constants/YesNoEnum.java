package com.mbappe.newsql.constants;

public enum YesNoEnum {
    YES(1, "是"), // 开始
    NO(2, "否");  // 结束

    private Integer code;
    private String desc;

    private YesNoEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public Integer getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public static boolean is(Integer code) {
        if (code == null) {
            return false;
        }
        for (YesNoEnum yesNo : YesNoEnum.values()) {
            if (yesNo.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNot(Integer code) {
        return !is(code);
    }

    public static YesNoEnum getEnum(Integer enable) {
        if (enable == null) {
            return null;
        }
        for (YesNoEnum enableEnum : YesNoEnum.values()) {
            if (enableEnum.getCode().equals(enable)) {
                return enableEnum;
            }
        }
        return NO;
    }
}

