package com.example.QuizPlatformApplication.controller.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class QuizProgressDTO implements Serializable {
    private Long quizId;
    private String ownerUsername;
    private Boolean hasEnded;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Float score;

    public QuizProgressDTO(Long quizId, String ownerUsername, Boolean hasEnded, LocalDateTime startTime, LocalDateTime endTime, Float score) {
        this.quizId = quizId;
        this.ownerUsername = ownerUsername;
        this.hasEnded = hasEnded;
        this.startTime = startTime;
        this.endTime = endTime;
        this.score = score;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public Boolean getHasEnded() {
        return hasEnded;
    }

    public void setHasEnded(Boolean hasEnded) {
        this.hasEnded = hasEnded;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
