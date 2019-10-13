package com.noslowq.newsql.constants;

/**
 * 关注等级,作为最后的严重情况判断
 *
 * @author gannicus
 */

public enum NoticeLevelEnum {

    GOOD(1, "还不错"),
    NOTICE(2, "需要关注"),
    WARNING(3, "警告"),
    CRITICAL(4, "严重");

    private Integer code;
    private String desc;

    NoticeLevelEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
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

    public static NoticeLevelEnum get(Integer code) {
        for (NoticeLevelEnum noticeLevelEnum : NoticeLevelEnum.values()) {
            if (code.equals(noticeLevelEnum.getCode())) {
                return noticeLevelEnum;
            }
        }
        return null;
    }
}
