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

    /**
     * Constructor
     * @param questionId question id
     * @param isMultipleChoice boolean value indicating if the question is multiple choice
     * @param questionText question text
     * @param possibleAnswers list of pairs of option ids and question texts
     */
    public QuestionInfoDTO(Long questionId, boolean isMultipleChoice, String questionText, List<Pair<Long, String>> possibleAnswers) {
        this.questionId = questionId;
        this.isMultipleChoice = isMultipleChoice;
        this.questionText = questionText;
        this.possibleAnswers = possibleAnswers;
    }

    /**
     * Constructor
     */
    public QuestionInfoDTO() {
    }

    /**
     * Getter for question id
     * @return question id
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * Setter for question id
     * @param questionId question id
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * Getter for boolean value indicating if the question is multiple choice
     * @return boolean value indicating if the question is multiple choice
     */
    public boolean isMultipleChoice() {
        return isMultipleChoice;
    }

    /**
     * Setter for boolean value indicating if the question is multiple choice
     * @param multipleChoice boolean value indicating if the question is multiple choice
     */
    public void setMultipleChoice(boolean multipleChoice) {
        isMultipleChoice = multipleChoice;
    }

    /**
     * Getter for question text
     * @return question text
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Setter for question text
     * @param questionText question text
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Getter for list of pairs of option ids and question texts
     * @return list of pairs of option ids and question texts
     */
    public List<Pair<Long, String>> getPossibleAnswers() {
        return possibleAnswers;
    }

    /**
     * Setter for list of pairs of option ids and question texts
     * @param possibleAnswers list of pairs of option ids and question texts
     */
    public void setPossibleAnswers(List<Pair<Long, String>> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }
}
