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

    /**
     * Constructor
     * @param questionId question id as string
     * @param answersIds list of answers ids as strings
     */
    public AnswerDTO(String questionId, List<String> answersIds) {
        this.questionId = questionId;
        this.answersIds = answersIds;
    }

    /**
     * Constructor
     */
    public AnswerDTO() {
    }

    /**
     * Getter for question id
     * @return question id as string
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * Setter for question id
     * @param questionId question id as string
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    /**
     * Getter for answers ids
     * @return list of answers ids as strings
     */
    public List<String> getAnswersIds() {
        return answersIds;
    }

    /**
     * Setter for answers ids
     * @param answersIds list of answers ids as strings
     */
    public void setAnswersIds(List<String> answersIds) {
        this.answersIds = answersIds;
    }
}
