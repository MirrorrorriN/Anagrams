package com.mirror.miniprogram.anagrams.pojo;

public class RiddleUser {
    private Long id;

    private String openid;

    private Long correctCount;

    private Long incorrectCount;

    private Long queryCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Long getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(Long correctCount) {
        this.correctCount = correctCount;
    }

    public Long getIncorrectCount() {
        return incorrectCount;
    }

    public void setIncorrectCount(Long incorrectCount) {
        this.incorrectCount = incorrectCount;
    }

    public Long getQueryCount() {
        return queryCount;
    }

    public void setQueryCount(Long queryCount) {
        this.queryCount = queryCount;
    }
}