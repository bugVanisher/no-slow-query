package com.mbappe.newsql.dto.req;

/**
 * @author: gannicus at 2018/8/14
 */
public class Action {

    private Long uid;

    private String action;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
