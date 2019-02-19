package com.mbappe.newsql.newsql.ddl;

import java.io.Serializable;

/**
 *
 * @author gannicus
 */
public class DbInfoDO implements Serializable {
    /**
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * Database Column Remarks:
     *   主键
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
     *   主机
     *
     *
     * @mbg.generated
     */
    private String mhost;

    /**
     * Database Column Remarks:
     *   端口
     *
     *
     * @mbg.generated
     */
    private String mport;

    /**
     * Database Column Remarks:
     *   用户名
     *
     *
     * @mbg.generated
     */
    private String muser;

    /**
     * Database Column Remarks:
     *   密码
     *
     *
     * @mbg.generated
     */
    private String mpwd;

    /**
     * Database Column Remarks:
     *   数据库
     *
     *
     * @mbg.generated
     */
    private String mdb;

    /**
     * Database Column Remarks:
     *   生成时间
     *
     *
     * @mbg.generated
     */
    private Integer ctime;

    /**
     *
     * @return the value of db_info.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id the value for db_info.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return the value of db_info.app_name
     *
     * @mbg.generated
     */
    public String getAppName() {
        return appName;
    }

    /**
     *
     * @param appName the value for db_info.app_name
     *
     * @mbg.generated
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     *
     * @return the value of db_info.mhost
     *
     * @mbg.generated
     */
    public String getMhost() {
        return mhost;
    }

    /**
     *
     * @param mhost the value for db_info.mhost
     *
     * @mbg.generated
     */
    public void setMhost(String mhost) {
        this.mhost = mhost;
    }

    /**
     *
     * @return the value of db_info.mport
     *
     * @mbg.generated
     */
    public String getMport() {
        return mport;
    }

    /**
     *
     * @param mport the value for db_info.mport
     *
     * @mbg.generated
     */
    public void setMport(String mport) {
        this.mport = mport;
    }

    /**
     *
     * @return the value of db_info.muser
     *
     * @mbg.generated
     */
    public String getMuser() {
        return muser;
    }

    /**
     *
     * @param muser the value for db_info.muser
     *
     * @mbg.generated
     */
    public void setMuser(String muser) {
        this.muser = muser;
    }

    /**
     *
     * @return the value of db_info.mpwd
     *
     * @mbg.generated
     */
    public String getMpwd() {
        return mpwd;
    }

    /**
     *
     * @param mpwd the value for db_info.mpwd
     *
     * @mbg.generated
     */
    public void setMpwd(String mpwd) {
        this.mpwd = mpwd;
    }

    /**
     *
     * @return the value of db_info.mdb
     *
     * @mbg.generated
     */
    public String getMdb() {
        return mdb;
    }

    /**
     *
     * @param mdb the value for db_info.mdb
     *
     * @mbg.generated
     */
    public void setMdb(String mdb) {
        this.mdb = mdb;
    }

    /**
     *
     * @return the value of db_info.ctime
     *
     * @mbg.generated
     */
    public Integer getCtime() {
        return ctime;
    }

    /**
     *
     * @param ctime the value for db_info.ctime
     *
     * @mbg.generated
     */
    public void setCtime(Integer ctime) {
        this.ctime = ctime;
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
        sb.append(", mhost=").append(mhost);
        sb.append(", mport=").append(mport);
        sb.append(", muser=").append(muser);
        sb.append(", mpwd=").append(mpwd);
        sb.append(", mdb=").append(mdb);
        sb.append(", ctime=").append(ctime);
        sb.append("]");
        return sb.toString();
    }
}