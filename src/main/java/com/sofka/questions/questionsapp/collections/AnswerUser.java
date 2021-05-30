package com.sofka.questions.questionsapp.collections;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class AnswerUser {

    private String questionId;
    private  String answerId;
    private String action;
    private String userId;
    private LocalDateTime dateAnswer;
    private Boolean update;

    public AnswerUser(String questionId, String answerId, String action, String userId, LocalDateTime dateAnswer, Boolean update) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.action = action;
        this.userId = userId;
        this.dateAnswer = dateAnswer;
        this.update = update;
    }

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    public LocalDateTime getDateAnswer() {
        return dateAnswer;
    }

    public void setDateAnswer(LocalDateTime dateAnswer) {
        this.dateAnswer = dateAnswer;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
