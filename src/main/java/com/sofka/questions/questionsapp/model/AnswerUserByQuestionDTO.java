package com.sofka.questions.questionsapp.model;

public class AnswerUserByQuestionDTO {
    private String userId;
    private String questionId;

    public AnswerUserByQuestionDTO(String userId, String questionId) {
        this.userId = userId;
        this.questionId = questionId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getUserId() {
        return userId;
    }
}
