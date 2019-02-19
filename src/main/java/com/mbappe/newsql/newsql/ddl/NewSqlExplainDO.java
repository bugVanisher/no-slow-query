package com.mbappe.newsql.newsql.ddl;

import java.io.Serializable;

/**
 *
 * @author gannicus
 */
public class NewSqlExplainDO implements Serializable {
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
    private Long eid;

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
     *   类型
     *
     *
     * @mbg.generated
     */
    private String selectType;

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
     *   partitions
     *
     *
     * @mbg.generated
     */
    private String partitions;

    /**
     * Database Column Remarks:
     *   查询类型
     *
     *
     * @mbg.generated
     */
    private String type;

    /**
     * Database Column Remarks:
     *   可能的索引
     *
     *
     * @mbg.generated
     */
    private String possibleKeys;

    /**
     * Database Column Remarks:
     *   实际使用的索引
     *
     *
     * @mbg.generated
     */
    private String key;

    /**
     * Database Column Remarks:
     *   索引长度
     *
     *
     * @mbg.generated
     */
    private String keyLen;

    /**
     * Database Column Remarks:
     *   关联类型
     *
     *
     * @mbg.generated
     */
    private String ref;

    /**
     * Database Column Remarks:
     *   扫描的行数
     *
     *
     * @mbg.generated
     */
    private String rows;

    /**
     * Database Column Remarks:
     *   过滤
     *
     *
     * @mbg.generated
     */
    private String filtered;

    /**
     * Database Column Remarks:
     *   扩展信息
     *
     *
     * @mbg.generated
     */
    private String extra;

    /**
     * Database Column Remarks:
     *   严重等级
     *
     *
     * @mbg.generated
     */
    private String slevel;

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
     *   sql语句,冗余
     *
     *
     * @mbg.generated
     */
    private String newSql;

    /**
     *
     * @return the value of new_sqls_explain.eid
     *
     * @mbg.generated
     */
    public Long getEid() {
        return eid;
    }

    /**
     *
     * @param eid the value for new_sqls_explain.eid
     *
     * @mbg.generated
     */
    public void setEid(Long eid) {
        this.eid = eid;
    }

    /**
     *
     * @return the value of new_sqls_explain.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id the value for new_sqls_explain.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return the value of new_sqls_explain.select_type
     *
     * @mbg.generated
     */
    public String getSelectType() {
        return selectType;
    }

    /**
     *
     * @param selectType the value for new_sqls_explain.select_type
     *
     * @mbg.generated
     */
    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    /**
     *
     * @return the value of new_sqls_explain.tablename
     *
     * @mbg.generated
     */
    public String getTablename() {
        return tablename;
    }

    /**
     *
     * @param tablename the value for new_sqls_explain.tablename
     *
     * @mbg.generated
     */
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    /**
     *
     * @return the value of new_sqls_explain.partitions_
     *
     * @mbg.generated
     */
    public String getPartitions() {
        return partitions;
    }

    /**
     *
     * @param partitions the value for new_sqls_explain.partitions_
     *
     * @mbg.generated
     */
    public void setPartitions(String partitions) {
        this.partitions = partitions;
    }

    /**
     *
     * @return the value of new_sqls_explain.type
     *
     * @mbg.generated
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type the value for new_sqls_explain.type
     *
     * @mbg.generated
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return the value of new_sqls_explain.possible_keys
     *
     * @mbg.generated
     */
    public String getPossibleKeys() {
        return possibleKeys;
    }

    /**
     *
     * @param possibleKeys the value for new_sqls_explain.possible_keys
     *
     * @mbg.generated
     */
    public void setPossibleKeys(String possibleKeys) {
        this.possibleKeys = possibleKeys;
    }

    /**
     *
     * @return the value of new_sqls_explain.key_
     *
     * @mbg.generated
     */
    public String getKey() {
        return key;
    }

    /**
     *
     * @param key the value for new_sqls_explain.key_
     *
     * @mbg.generated
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     *
     * @return the value of new_sqls_explain.key_len
     *
     * @mbg.generated
     */
    public String getKeyLen() {
        return keyLen;
    }

    /**
     *
     * @param keyLen the value for new_sqls_explain.key_len
     *
     * @mbg.generated
     */
    public void setKeyLen(String keyLen) {
        this.keyLen = keyLen;
    }

    /**
     *
     * @return the value of new_sqls_explain.ref
     *
     * @mbg.generated
     */
    public String getRef() {
        return ref;
    }

    /**
     *
     * @param ref the value for new_sqls_explain.ref
     *
     * @mbg.generated
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    /**
     *
     * @return the value of new_sqls_explain.rows_
     *
     * @mbg.generated
     */
    public String getRows() {
        return rows;
    }

    /**
     *
     * @param rows the value for new_sqls_explain.rows_
     *
     * @mbg.generated
     */
    public void setRows(String rows) {
        this.rows = rows;
    }

    /**
     *
     * @return the value of new_sqls_explain.filtered
     *
     * @mbg.generated
     */
    public String getFiltered() {
        return filtered;
    }

    /**
     *
     * @param filtered the value for new_sqls_explain.filtered
     *
     * @mbg.generated
     */
    public void setFiltered(String filtered) {
        this.filtered = filtered;
    }

    /**
     *
     * @return the value of new_sqls_explain.Extra
     *
     * @mbg.generated
     */
    public String getExtra() {
        return extra;
    }

    /**
     *
     * @param extra the value for new_sqls_explain.Extra
     *
     * @mbg.generated
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     *
     * @return the value of new_sqls_explain.slevel
     *
     * @mbg.generated
     */
    public String getSlevel() {
        return slevel;
    }

    /**
     *
     * @param slevel the value for new_sqls_explain.slevel
     *
     * @mbg.generated
     */
    public void setSlevel(String slevel) {
        this.slevel = slevel;
    }

    /**
     *
     * @return the value of new_sqls_explain.ctime
     *
     * @mbg.generated
     */
    public Integer getCtime() {
        return ctime;
    }

    /**
     *
     * @param ctime the value for new_sqls_explain.ctime
     *
     * @mbg.generated
     */
    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    /**
     *
     * @return the value of new_sqls_explain.utime
     *
     * @mbg.generated
     */
    public Integer getUtime() {
        return utime;
    }

    /**
     *
     * @param utime the value for new_sqls_explain.utime
     *
     * @mbg.generated
     */
    public void setUtime(Integer utime) {
        this.utime = utime;
    }

    /**
     *
     * @return the value of new_sqls_explain.new_sql
     *
     * @mbg.generated
     */
    public String getNewSql() {
        return newSql;
    }

    /**
     *
     * @param newSql the value for new_sqls_explain.new_sql
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
        sb.append(", eid=").append(eid);
        sb.append(", id=").append(id);
        sb.append(", selectType=").append(selectType);
        sb.append(", tablename=").append(tablename);
        sb.append(", partitions=").append(partitions);
        sb.append(", type=").append(type);
        sb.append(", possibleKeys=").append(possibleKeys);
        sb.append(", key=").append(key);
        sb.append(", keyLen=").append(keyLen);
        sb.append(", ref=").append(ref);
        sb.append(", rows=").append(rows);
        sb.append(", filtered=").append(filtered);
        sb.append(", extra=").append(extra);
        sb.append(", slevel=").append(slevel);
        sb.append(", ctime=").append(ctime);
        sb.append(", utime=").append(utime);
        sb.append(", newSql=").append(newSql);
        sb.append("]");
        return sb.toString();
    }
}