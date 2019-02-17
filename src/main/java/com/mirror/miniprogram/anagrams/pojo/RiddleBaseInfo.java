package com.mirror.miniprogram.anagrams.pojo;

public class RiddleBaseInfo {
    private Long id;

    private String riddleQuestion;

    private String riddleHint;

    private String riddleAnswer;

    private String riddleExplanation;

    private Long solvedCount;

    private Integer riddleType;

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

    public Long getSolvedCount() {
        return solvedCount;
    }

    public void setSolvedCount(Long solvedCount) {
        this.solvedCount = solvedCount;
    }

    public Integer getRiddleType() {
        return riddleType;
    }

    public void setRiddleType(Integer riddleType) {
        this.riddleType = riddleType;
    }
}