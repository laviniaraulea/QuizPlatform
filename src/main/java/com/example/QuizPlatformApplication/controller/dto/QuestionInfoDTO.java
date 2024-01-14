package com.example.QuizPlatformApplication.controller.dto;

import org.springframework.data.util.Pair;

import java.io.Serializable;
import java.util.List;

/**
 * DTO class for question info
 */
public class QuestionInfoDTO implements Serializable {
    /**
     * Question id
     */
    private Long questionId;
    /**
     * Boolean value indicating if the question is multiple choice
     */
    private boolean isMultipleChoice;
    /**
     * Question text
     */
    private String questionText;
    /**
     * List of pairs of option ids and question texts
     */
    private List<Pair<Long, String>> possibleAnswers;

    public QuestionInfoDTO(Long questionId, boolean isMultipleChoice, String questionText, List<Pair<Long, String>> possibleAnswers) {
        this.questionId = questionId;
        this.isMultipleChoice = isMultipleChoice;
        this.questionText = questionText;
        this.possibleAnswers = possibleAnswers;
    }

    public QuestionInfoDTO() {
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public boolean isMultipleChoice() {
        return isMultipleChoice;
    }

    public void setMultipleChoice(boolean multipleChoice) {
        isMultipleChoice = multipleChoice;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<Pair<Long, String>> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<Pair<Long, String>> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }
}
