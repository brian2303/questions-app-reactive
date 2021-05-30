package com.sofka.questions.questionsapp.model;

import java.time.LocalDateTime;
import java.util.Optional;

public class UpdateAnswerDTO {

    private String questionId;
    private  String answerId;
    private String action;
    private String userId;
    private LocalDateTime dateAnswer;
    private Boolean update;

    public UpdateAnswerDTO() {
    }

    public UpdateAnswerDTO(Boolean update,String questionId, String answerId, String action, String userId, LocalDateTime answerUser) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.action = action;
        this.userId = userId;
        this.dateAnswer = answerUser;
        this.update=update;
    }

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
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

    public LocalDateTime getDateAnswer() {
        return Optional.of(dateAnswer).orElse(LocalDateTime.now());
    }

    public void setDateAnswer(LocalDateTime dateAnswer) {
        this.dateAnswer = dateAnswer;
    }
}
