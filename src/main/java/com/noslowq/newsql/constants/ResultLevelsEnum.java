package com.noslowq.newsql.constants;

/**
 *  查询计划结果
 */
public enum ResultLevelsEnum {

    UNKNOWN(0, "未知", NoticeLevelEnum.NOTICE, ""),
    MAYBE_GOOD(1, "貌似不错的查询", NoticeLevelEnum.GOOD, "success"),
    TYPE_IS_ALL(2, "type为All", NoticeLevelEnum.CRITICAL, "danger"),
    EXTRA_IS_BAD(3, "Extra存在filesort or temporary", NoticeLevelEnum.CRITICAL, "danger"),
    NO_WHERE_CLAUSE(4, "没有where条件", NoticeLevelEnum.CRITICAL, "danger"),
    LARGE_SPAN_LIMIT(5, "limit行数过大", NoticeLevelEnum.WARNING, "warning"),
    ROWS_IS_TOO_MANY(6, "rows行数过大", NoticeLevelEnum.WARNING, "warning"),
    TYPE_IS_RANGE(7, "type为range", NoticeLevelEnum.NOTICE, "warning");

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NoticeLevelEnum getNoticeLevelEnum() {
        return noticeLevelEnum;
    }

    public void setNoticeLevelEnum(NoticeLevelEnum noticeLevelEnum) {
        this.noticeLevelEnum = noticeLevelEnum;
    }

    private Integer code;
    private String desc;
    private NoticeLevelEnum noticeLevelEnum;
    private String type;

    ResultLevelsEnum(Integer code, String desc, NoticeLevelEnum noticeLevelEnum, String type) {
        this.code = code;
        this.desc = desc;
        this.noticeLevelEnum = noticeLevelEnum;
        this.type = type;
    }

    public static ResultLevelsEnum getResultLevelsEnum(Integer code) {
        for (ResultLevelsEnum resultLevelsEnum : ResultLevelsEnum.values()) {
            if (code.equals(resultLevelsEnum.getCode())) {
                return resultLevelsEnum;
            }
        }
        return null;
    }

}
