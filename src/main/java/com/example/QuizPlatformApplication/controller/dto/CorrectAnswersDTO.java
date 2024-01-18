package com.example.QuizPlatformApplication.controller.dto;

import org.springframework.data.util.Pair;

import java.util.List;

/**
 * DTO class for correct answers
 */
public class CorrectAnswersDTO {
    /**
     * Quiz entry id
     */
    private Long quizEntryId;
    /**
     * List of pairs of options and explanations
     */
    private List<Pair<Long, String>> optionsAndExplanations;
    /**
     * List of correct answers ids
     */
    private List<Long> correctAnswersIds;

    /**
     * Constructor
     * @param quizEntryId quiz entry id
     * @param optionsAndExplanations list of pairs of options and explanations
     * @param correctAnswersIds list of correct answers ids
     */
    public CorrectAnswersDTO(Long quizEntryId, List<Pair<Long, String>> optionsAndExplanations, List<Long> correctAnswersIds) {
        this.quizEntryId = quizEntryId;
        this.optionsAndExplanations = optionsAndExplanations;
        this.correctAnswersIds = correctAnswersIds;
    }

    /**
     * Constructor
     */
    public CorrectAnswersDTO() {
    }

    /**
     * Getter for quiz entry id
     * @return quiz entry id
     */
    public Long getQuizEntryId() {
        return quizEntryId;
    }

    /**
     * Setter for quiz entry id
     * @param quizEntryId quiz entry id
     */
    public void setQuizEntryId(Long quizEntryId) {
        this.quizEntryId = quizEntryId;
    }

    /**
     * Getter for options and explanations
     * @return list of pairs of options and explanations
     */
    public List<Pair<Long, String>> getOptionsAndExplanations() {
        return optionsAndExplanations;
    }

    /**
     * Setter for options and explanations
     * @param optionsAndExplanations list of pairs of options and explanations
     */
    public void setOptionsAndExplanations(List<Pair<Long, String>> optionsAndExplanations) {
        this.optionsAndExplanations = optionsAndExplanations;
    }

    /**
     * Getter for correct answers ids
     * @return list of correct answers ids
     */
    public List<Long> getCorrectAnswersIds() {
        return correctAnswersIds;
    }

    /**
     * Setter for correct answers ids
     * @param correctAnswersIds list of correct answers ids
     */
    public void setCorrectAnswersIds(List<Long> correctAnswersIds) {
        this.correctAnswersIds = correctAnswersIds;
    }
}
