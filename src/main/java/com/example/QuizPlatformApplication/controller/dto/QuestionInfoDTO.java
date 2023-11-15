package com.example.QuizPlatformApplication.controller.dto;

import org.springframework.data.util.Pair;

import java.io.Serializable;
import java.util.List;

public class QuestionInfoDTO implements Serializable {
    private Long questionId;
    private boolean isMultipleChoice;
    private String questionText;
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
