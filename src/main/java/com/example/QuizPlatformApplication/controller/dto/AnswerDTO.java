package com.example.QuizPlatformApplication.controller.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO class for answers
 */
public class AnswerDTO implements Serializable {
    /**
     * Question id as string
     */
    private String questionId;
    /**
     * List of answers ids as strings
     */
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
