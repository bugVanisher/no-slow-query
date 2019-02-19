package com.mbappe.newsql.newsql.ddl;

import java.io.Serializable;

/**
 *
 * @author gannicus
 */
public class TemplateSqlDO implements Serializable {
    /**
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * Database Column Remarks:
     *   主键id
     *
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   应用ID
     *
     *
     * @mbg.generated
     */
    private String appName;

    /**
     * Database Column Remarks:
     *   表
     *
     *
     * @mbg.generated
     */
    private String tablename;

    /**
     * Database Column Remarks:
     *   新sql的md5值
     *
     *
     * @mbg.generated
     */
    private String sqlMd5;

    /**
     * Database Column Remarks:
     *   第一次发现,来源环境
     *
     *
     * @mbg.generated
     */
    private String env;

    /**
     * Database Column Remarks:
     *   最终严重等级分类
     *
     *
     * @mbg.generated
     */
    private String finalLevels;

    /**
     * Database Column Remarks:
     *   状态,1未处理，2已忽略,3已处理
     *
     *
     * @mbg.generated
     */
    private Short status;

    /**
     * Database Column Remarks:
     *   sql类型,1参数化，2无参数化
     *
     *
     * @mbg.generated
     */
    private Short sqlType;

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
     * Database Column Remarks:
     *   新sql
     *
     *
     * @mbg.generated
     */
    private String newSql;

    /**
     *
     * @return the value of uniq_sqls.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id the value for uniq_sqls.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return the value of uniq_sqls.app_name
     *
     * @mbg.generated
     */
    public String getAppName() {
        return appName;
    }

    /**
     *
     * @param appName the value for uniq_sqls.app_name
     *
     * @mbg.generated
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     *
     * @return the value of uniq_sqls.tablename
     *
     * @mbg.generated
     */
    public String getTablename() {
        return tablename;
    }

    /**
     *
     * @param tablename the value for uniq_sqls.tablename
     *
     * @mbg.generated
     */
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    /**
     *
     * @return the value of uniq_sqls.sql_md5
     *
     * @mbg.generated
     */
    public String getSqlMd5() {
        return sqlMd5;
    }

    /**
     *
     * @param sqlMd5 the value for uniq_sqls.sql_md5
     *
     * @mbg.generated
     */
    public void setSqlMd5(String sqlMd5) {
        this.sqlMd5 = sqlMd5;
    }

    /**
     *
     * @return the value of uniq_sqls.env
     *
     * @mbg.generated
     */
    public String getEnv() {
        return env;
    }

    /**
     *
     * @param env the value for uniq_sqls.env
     *
     * @mbg.generated
     */
    public void setEnv(String env) {
        this.env = env;
    }

    /**
     *
     * @return the value of uniq_sqls.final_levels
     *
     * @mbg.generated
     */
    public String getFinalLevels() {
        return finalLevels;
    }

    /**
     *
     * @param finalLevels the value for uniq_sqls.final_levels
     *
     * @mbg.generated
     */
    public void setFinalLevels(String finalLevels) {
        this.finalLevels = finalLevels;
    }

    /**
     *
     * @return the value of uniq_sqls.status_
     *
     * @mbg.generated
     */
    public Short getStatus() {
        return status;
    }

    /**
     *
     * @param status the value for uniq_sqls.status_
     *
     * @mbg.generated
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     *
     * @return the value of uniq_sqls.sql_type
     *
     * @mbg.generated
     */
    public Short getSqlType() {
        return sqlType;
    }

    /**
     *
     * @param sqlType the value for uniq_sqls.sql_type
     *
     * @mbg.generated
     */
    public void setSqlType(Short sqlType) {
        this.sqlType = sqlType;
    }

    /**
     *
     * @return the value of uniq_sqls.ctime
     *
     * @mbg.generated
     */
    public Integer getCtime() {
        return ctime;
    }

    /**
     *
     * @param ctime the value for uniq_sqls.ctime
     *
     * @mbg.generated
     */
    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    /**
     *
     * @return the value of uniq_sqls.utime
     *
     * @mbg.generated
     */
    public Integer getUtime() {
        return utime;
    }

    /**
     *
     * @param utime the value for uniq_sqls.utime
     *
     * @mbg.generated
     */
    public void setUtime(Integer utime) {
        this.utime = utime;
    }

    /**
     *
     * @return the value of uniq_sqls.new_sql
     *
     * @mbg.generated
     */
    public String getNewSql() {
        return newSql;
    }

    /**
     *
     * @param newSql the value for uniq_sqls.new_sql
     *
     * @mbg.generated
     */
    public void setNewSql(String newSql) {
        this.newSql = newSql;
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
        sb.append(", tablename=").append(tablename);
        sb.append(", sqlMd5=").append(sqlMd5);
        sb.append(", env=").append(env);
        sb.append(", finalLevels=").append(finalLevels);
        sb.append(", status=").append(status);
        sb.append(", sqlType=").append(sqlType);
        sb.append(", ctime=").append(ctime);
        sb.append(", utime=").append(utime);
        sb.append(", newSql=").append(newSql);
        sb.append("]");
        return sb.toString();
    }
}