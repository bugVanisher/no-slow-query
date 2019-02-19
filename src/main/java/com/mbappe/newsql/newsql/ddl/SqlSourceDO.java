package com.mbappe.newsql.newsql.ddl;

import java.io.Serializable;

/**
 *
 * @author gannicus
 */
public class SqlSourceDO implements Serializable {
    /**
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * Database Column Remarks:
     *   new sql id
     *
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   哪个应用
     *
     *
     * @mbg.generated
     */
    private String appName;

    /**
     * Database Column Remarks:
     *   来源环境
     *
     *
     * @mbg.generated
     */
    private String env;

    /**
     * Database Column Remarks:
     *   触发sql的action
     *
     *
     * @mbg.generated
     */
    private String trace;

    /**
     * Database Column Remarks:
     *   生成时间
     *
     *
     * @mbg.generated
     */
    private Integer ctime;

    /**
     * Database Column Remarks:
     *   更新时间
     *
     *
     * @mbg.generated
     */
    private Integer utime;

    /**
     *
     * @return the value of sql_sources.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id the value for sql_sources.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return the value of sql_sources.app_name
     *
     * @mbg.generated
     */
    public String getAppName() {
        return appName;
    }

    /**
     *
     * @param appName the value for sql_sources.app_name
     *
     * @mbg.generated
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     *
     * @return the value of sql_sources.env
     *
     * @mbg.generated
     */
    public String getEnv() {
        return env;
    }

    /**
     *
     * @param env the value for sql_sources.env
     *
     * @mbg.generated
     */
    public void setEnv(String env) {
        this.env = env;
    }

    /**
     *
     * @return the value of sql_sources.trace
     *
     * @mbg.generated
     */
    public String getTrace() {
        return trace;
    }

    /**
     *
     * @param trace the value for sql_sources.trace
     *
     * @mbg.generated
     */
    public void setTrace(String trace) {
        this.trace = trace;
    }

    /**
     *
     * @return the value of sql_sources.ctime
     *
     * @mbg.generated
     */
    public Integer getCtime() {
        return ctime;
    }

    /**
     *
     * @param ctime the value for sql_sources.ctime
     *
     * @mbg.generated
     */
    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    /**
     *
     * @return the value of sql_sources.utime
     *
     * @mbg.generated
     */
    public Integer getUtime() {
        return utime;
    }

    /**
     *
     * @param utime the value for sql_sources.utime
     *
     * @mbg.generated
     */
    public void setUtime(Integer utime) {
        this.utime = utime;
    }

    /**
     * @return
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append(", appName=").append(appName);
        sb.append(", env=").append(env);
        sb.append(", trace=").append(trace);
        sb.append(", ctime=").append(ctime);
        sb.append(", utime=").append(utime);
        sb.append("]");
        return sb.toString();
    }
}