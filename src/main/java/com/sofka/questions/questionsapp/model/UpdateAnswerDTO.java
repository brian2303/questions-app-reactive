package com.sofka.questions.questionsapp.model;

public class UpdateAnswerDTO {
    private String questionId;
    private  String answerId;
    private String action;

    public UpdateAnswerDTO(String questionId, String answerId, String action) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.action = action;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
