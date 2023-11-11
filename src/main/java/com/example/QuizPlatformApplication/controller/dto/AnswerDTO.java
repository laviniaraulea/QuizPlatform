package com.example.QuizPlatformApplication.controller.dto;

import java.io.Serializable;
import java.util.List;

public class AnswerDTO implements Serializable {
    private String questionId;
    private List<String> answersIds;

    public AnswerDTO(String questionId, List<String> answersIds) {
        this.questionId = questionId;
        this.answersIds = answersIds;
    }

    public AnswerDTO() {
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public List<String> getAnswersIds() {
        return answersIds;
    }

    public void setAnswersIds(List<String> answersIds) {
        this.answersIds = answersIds;
    }
}
