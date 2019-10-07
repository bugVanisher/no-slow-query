package com.mbappe.newsql.dto.req;

/**
 * @author: gannicus at 2018/8/10
 */
public class SearchCriteria {

    private String appName;

    private String tablename;

    private String label;

    private Integer sctime;

    private Integer ectime;

    private Integer page = 1;

    private Integer size = 20;

    private Boolean isAll;

    public Boolean getisAll() {
        return isAll;
    }

    public void setisAll(Boolean all) {
        this.isAll = all;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getSctime() {
        return sctime;
    }

    public void setSctime(Integer sctime) {
        this.sctime = sctime;
    }

    public Integer getEctime() {
        return ectime;
    }

    public void setEctime(Integer ectime) {
        this.ectime = ectime;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
