package com.example.QuizPlatformApplication.controller.dto;

import java.util.List;

public class EndQuizRequest {
    private String username;
    private List<AnswerDTO> userAnswers;

    public EndQuizRequest(String username, List<AnswerDTO> userAnswers) {
        this.username = username;
        this.userAnswers = userAnswers;
    }

    public EndQuizRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<AnswerDTO> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<AnswerDTO> userAnswers) {
        this.userAnswers = userAnswers;
    }
}
