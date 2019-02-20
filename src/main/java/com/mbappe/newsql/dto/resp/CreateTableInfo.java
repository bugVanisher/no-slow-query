package com.mbappe.newsql.dto.resp;

/**
 * @author: gannicus at 2018/9/10
 */
public class CreateTableInfo {

    private boolean hasMoreDbSource = false;

    private String createTableContent;

    private String templateSql;

    public String getTemplateSql() {
        return templateSql;
    }

    public void setTemplateSql(String templateSql) {
        this.templateSql = templateSql;
    }

    public boolean isHasMoreDbSource() {
        return hasMoreDbSource;
    }

    public void setHasMoreDbSource(boolean hasMoreDbSource) {
        this.hasMoreDbSource = hasMoreDbSource;
    }

    public String getCreateTableContent() {
        return createTableContent;
    }

    public void setCreateTableContent(String createTableContent) {
        this.createTableContent = createTableContent;
    }
}
