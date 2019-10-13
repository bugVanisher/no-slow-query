package com.noslowq.newsql.dto.req;

/**
 * @author: gannicus at 2018/9/6
 */
public class Handle {

    private Long uid;
    private String problemDesc;
    private String solution;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getProblemDesc() {
        return problemDesc;
    }

    public void setProblemDesc(String problemDesc) {
        this.problemDesc = problemDesc;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
