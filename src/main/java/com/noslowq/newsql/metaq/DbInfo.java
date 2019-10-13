package com.noslowq.newsql.metaq;

import java.io.Serializable;

import static java.util.Objects.hash;

public class DbInfo implements Serializable {

    private static final long serialVersionUID = 209689902099829394L;

    private String mHost;
    private String mPort;
    private String mUserName;
    private String mPwd;
    private String db;

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getmHost() {
        return mHost;
    }

    public void setmHost(String mHost) {
        this.mHost = mHost;
    }

    public String getmPort() {
        return mPort;
    }

    public void setmPort(String mPort) {
        this.mPort = mPort;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmPwd() {
        return mPwd;
    }

    public void setmPwd(String mPwd) {
        this.mPwd = mPwd;
    }

    @Override
    public int hashCode() {
        return hash(mHost, mPort, mUserName, db);
    }
}