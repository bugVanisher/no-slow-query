package com.mbappe.newsql.newsql.ddl;

import java.io.Serializable;

/**
 *
 * @author gannicus
 */
public class NewSqlInfoDO implements Serializable {
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
     *   应用ID
     *
     *
     * @mbg.generated
     */
    private String appName;

    /**
     * Database Column Remarks:
     *   唯一SQL
     *
     *
     * @mbg.generated
     */
    private Long uniqId;

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
     *   数据库关联ID
     *
     *
     * @mbg.generated
     */
    private Long dbId;

    /**
     * Database Column Remarks:
     *   数据库名
     *
     *
     * @mbg.generated
     */
    private String dbName;

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
     *   出现次数
     *
     *
     * @mbg.generated
     */
    private Integer num;

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
     * @return the value of new_sqls_info.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id the value for new_sqls_info.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return the value of new_sqls_info.app_name
     *
     * @mbg.generated
     */
    public String getAppName() {
        return appName;
    }

    /**
     *
     * @param appName the value for new_sqls_info.app_name
     *
     * @mbg.generated
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     *
     * @return the value of new_sqls_info.uniq_id
     *
     * @mbg.generated
     */
    public Long getUniqId() {
        return uniqId;
    }

    /**
     *
     * @param uniqId the value for new_sqls_info.uniq_id
     *
     * @mbg.generated
     */
    public void setUniqId(Long uniqId) {
        this.uniqId = uniqId;
    }

    /**
     *
     * @return the value of new_sqls_info.tablename
     *
     * @mbg.generated
     */
    public String getTablename() {
        return tablename;
    }

    /**
     *
     * @param tablename the value for new_sqls_info.tablename
     *
     * @mbg.generated
     */
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    /**
     *
     * @return the value of new_sqls_info.db_id
     *
     * @mbg.generated
     */
    public Long getDbId() {
        return dbId;
    }

    /**
     *
     * @param dbId the value for new_sqls_info.db_id
     *
     * @mbg.generated
     */
    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    /**
     *
     * @return the value of new_sqls_info.db_name
     *
     * @mbg.generated
     */
    public String getDbName() {
        return dbName;
    }

    /**
     *
     * @param dbName the value for new_sqls_info.db_name
     *
     * @mbg.generated
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /**
     *
     * @return the value of new_sqls_info.sql_md5
     *
     * @mbg.generated
     */
    public String getSqlMd5() {
        return sqlMd5;
    }

    /**
     *
     * @param sqlMd5 the value for new_sqls_info.sql_md5
     *
     * @mbg.generated
     */
    public void setSqlMd5(String sqlMd5) {
        this.sqlMd5 = sqlMd5;
    }

    /**
     *
     * @return the value of new_sqls_info.num
     *
     * @mbg.generated
     */
    public Integer getNum() {
        return num;
    }

    /**
     *
     * @param num the value for new_sqls_info.num
     *
     * @mbg.generated
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     *
     * @return the value of new_sqls_info.ctime
     *
     * @mbg.generated
     */
    public Integer getCtime() {
        return ctime;
    }

    /**
     *
     * @param ctime the value for new_sqls_info.ctime
     *
     * @mbg.generated
     */
    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    /**
     *
     * @return the value of new_sqls_info.utime
     *
     * @mbg.generated
     */
    public Integer getUtime() {
        return utime;
    }

    /**
     *
     * @param utime the value for new_sqls_info.utime
     *
     * @mbg.generated
     */
    public void setUtime(Integer utime) {
        this.utime = utime;
    }

    /**
     *
     * @return the value of new_sqls_info.new_sql
     *
     * @mbg.generated
     */
    public String getNewSql() {
        return newSql;
    }

    /**
     *
     * @param newSql the value for new_sqls_info.new_sql
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
        sb.append(", uniqId=").append(uniqId);
        sb.append(", tablename=").append(tablename);
        sb.append(", dbId=").append(dbId);
        sb.append(", dbName=").append(dbName);
        sb.append(", sqlMd5=").append(sqlMd5);
        sb.append(", num=").append(num);
        sb.append(", ctime=").append(ctime);
        sb.append(", utime=").append(utime);
        sb.append(", newSql=").append(newSql);
        sb.append("]");
        return sb.toString();
    }
}