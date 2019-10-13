package com.noslowq.newsql.dto.req;

import java.util.List;

/**
 * @author: gannicus at 2018/8/30
 */
public class NotifySetting {

    private String appName;

    private List<String> notifyUsers;

    private List<Integer> notifyTypes;

    private boolean notifyNow;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<String> getNotifyUsers() {
        return notifyUsers;
    }

    public void setNotifyUsers(List<String> notifyUsers) {
        this.notifyUsers = notifyUsers;
    }

    public List<Integer> getNotifyTypes() {
        return notifyTypes;
    }

    public void setNotifyTypes(List<Integer> notifyTypes) {
        this.notifyTypes = notifyTypes;
    }

    public boolean isNotifyNow() {
        return notifyNow;
    }

    public void setNotifyNow(boolean notifyNow) {
        this.notifyNow = notifyNow;
    }
}
