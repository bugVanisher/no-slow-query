package com.mbappe.newsql.dto;

/**
 * @author: gannicus at 2018/7/25
 */
public class ExplainResult {

    /**
     * new sql id.
     * <p>字段名称： new_sqls_explain.id</p>
     */
    private Long id;

    /**
     * 类型.
     * <p>字段名称： new_sqls_explain.select_type</p>
     */
    private String select_type;

    /**
     * 表.
     * <p>字段名称： new_sqls_explain.table</p>
     */
    private String table;

    /**
     * 查询类型.
     * <p>字段名称： new_sqls_explain.partitions</p>
     */
    private String partitions;

    /**
     * 查询类型.
     * <p>字段名称： new_sqls_explain.type</p>
     */
    private String type;

    /**
     * 可能的索引.
     * <p>字段名称： new_sqls_explain.possible_keys</p>
     */
    private String possible_keys;

    /**
     * 实际使用的索引.
     * <p>字段名称： new_sqls_explain.key</p>
     */
    private String key;

    /**
     * 索引长度.
     * <p>字段名称： new_sqls_explain.key_len</p>
     */
    private String key_len;

    /**
     * 关联类型.
     * <p>字段名称： new_sqls_explain.ref</p>
     */
    private String ref;

    /**
     * 扫描的行数.
     * <p>字段名称： new_sqls_explain.rows</p>
     */
    private String rows;

    /**
     * 扫描的行数.
     * <p>字段名称： new_sqls_explain.filtered</p>
     */
    private String filtered;

    /**
     * 扩展信息.
     * <p>字段名称： new_sqls_explain.Extra</p>
     */
    private String Extra;

    /**
     * <p>字段名称： new_sqls_explain.slevel</p>
     */
    private Short slevel;

    /**
     * <p>字段名称： new_sqls_explain.new_sql</p>
     */
    private String new_sql;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSelect_type() {
        return select_type;
    }

    public void setSelect_type(String select_type) {
        this.select_type = select_type;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPossible_keys() {
        return possible_keys;
    }

    public void setPossible_keys(String possible_keys) {
        this.possible_keys = possible_keys;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey_len() {
        return key_len;
    }

    public void setKey_len(String key_len) {
        this.key_len = key_len;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getExtra() {
        return Extra;
    }

    public void setExtra(String extra) {
        Extra = extra;
    }

    public Short getSlevel() {
        return slevel;
    }

    public void setSlevel(Short slevel) {
        this.slevel = slevel;
    }

    public String getNew_sql() {
        return new_sql;
    }

    public void setNew_sql(String new_sql) {
        this.new_sql = new_sql;
    }

    public String getPartitions() {
        return partitions;
    }

    public void setPartitions(String partitions) {
        this.partitions = partitions;
    }

    public String getFiltered() {
        return filtered;
    }

    public void setFiltered(String filtered) {
        this.filtered = filtered;
    }
}
