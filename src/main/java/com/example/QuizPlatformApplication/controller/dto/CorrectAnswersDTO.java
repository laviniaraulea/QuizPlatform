package com.example.QuizPlatformApplication.controller.dto;

import org.springframework.data.util.Pair;

import java.util.List;

public class CorrectAnswersDTO {
    private Long quizEntryId;
    private List<Pair<Long, String>> optionsAndExplanations;
    private List<Long> correctAnswersIds;

    public CorrectAnswersDTO(Long quizEntryId, List<Pair<Long, String>> optionsAndExplanations, List<Long> correctAnswersIds) {
        this.quizEntryId = quizEntryId;
        this.optionsAndExplanations = optionsAndExplanations;
        this.correctAnswersIds = correctAnswersIds;
    }

    public CorrectAnswersDTO() {
    }

    public Long getQuizEntryId() {
        return quizEntryId;
    }

    public void setQuizEntryId(Long quizEntryId) {
        this.quizEntryId = quizEntryId;
    }

    public List<Pair<Long, String>> getOptionsAndExplanations() {
        return optionsAndExplanations;
    }

    public void setOptionsAndExplanations(List<Pair<Long, String>> optionsAndExplanations) {
        this.optionsAndExplanations = optionsAndExplanations;
    }

    public List<Long> getCorrectAnswersIds() {
        return correctAnswersIds;
    }

    public void setCorrectAnswersIds(List<Long> correctAnswersIds) {
        this.correctAnswersIds = correctAnswersIds;
    }
}
