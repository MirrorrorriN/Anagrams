package com.mirror.miniprogram.anagrams.pojo;

import java.util.Date;

public class RiddleBaseInfo {
    private Long id;

    private String riddleQuestion;

    private String riddleHint;

    private String riddleAnswer;

    private String riddleExplanation;

    private Integer solvedCount;

    private Integer triedCount;

    private Integer queryCount;

    private Integer answerCount;

    private Integer riddleType;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRiddleQuestion() {
        return riddleQuestion;
    }

    public void setRiddleQuestion(String riddleQuestion) {
        this.riddleQuestion = riddleQuestion == null ? null : riddleQuestion.trim();
    }

    public String getRiddleHint() {
        return riddleHint;
    }

    public void setRiddleHint(String riddleHint) {
        this.riddleHint = riddleHint == null ? null : riddleHint.trim();
    }

    public String getRiddleAnswer() {
        return riddleAnswer;
    }

    public void setRiddleAnswer(String riddleAnswer) {
        this.riddleAnswer = riddleAnswer == null ? null : riddleAnswer.trim();
    }

    public String getRiddleExplanation() {
        return riddleExplanation;
    }

    public void setRiddleExplanation(String riddleExplanation) {
        this.riddleExplanation = riddleExplanation == null ? null : riddleExplanation.trim();
    }

    public Integer getSolvedCount() {
        return solvedCount;
    }

    public void setSolvedCount(Integer solvedCount) {
        this.solvedCount = solvedCount;
    }

    public Integer getTriedCount() {
        return triedCount;
    }

    public void setTriedCount(Integer triedCount) {
        this.triedCount = triedCount;
    }

    public Integer getQueryCount() {
        return queryCount;
    }

    public void setQueryCount(Integer queryCount) {
        this.queryCount = queryCount;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public Integer getRiddleType() {
        return riddleType;
    }

    public void setRiddleType(Integer riddleType) {
        this.riddleType = riddleType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}