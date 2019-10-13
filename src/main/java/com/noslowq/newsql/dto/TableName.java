package com.noslowq.newsql.dto;

/**
 * @author: gannicus at 2018/12/10
 */
public class TableName {

    private String logicTableName;

    private String realTableName;

    public String getLogicTableName() {
        return logicTableName;
    }

    public void setLogicTableName(String logicTableName) {
        this.logicTableName = logicTableName;
    }

    public String getRealTableName() {
        return realTableName;
    }

    public void setRealTableName(String realTableName) {
        this.realTableName = realTableName;
    }
}
