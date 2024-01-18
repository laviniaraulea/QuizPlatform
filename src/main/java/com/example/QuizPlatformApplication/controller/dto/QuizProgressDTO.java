package com.example.QuizPlatformApplication.controller.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO class for quiz progress
 */
public class QuizProgressDTO implements Serializable {
    /**
     * Quiz id
     */
    private Long quizId;
    /**
     * Quiz owner username
     */
    private String ownerUsername;
    /**
     * Boolean value indicating if the quiz has ended
     */
    private Boolean hasEnded;
    /**
     * Quiz start time
     */
    private LocalDateTime startTime;
    /**
     * Quiz end time
     */
    private LocalDateTime endTime;
    /**
     * Quiz score
     */
    private Float score;

    /**
     * Constructor
     * @param quizId quiz id
     * @param ownerUsername quiz owner username
     * @param hasEnded boolean value indicating if the quiz has ended
     * @param startTime quiz start time
     * @param endTime quiz end time
     * @param score quiz score
     */
    public QuizProgressDTO(Long quizId, String ownerUsername, Boolean hasEnded, LocalDateTime startTime, LocalDateTime endTime, Float score) {
        this.quizId = quizId;
        this.ownerUsername = ownerUsername;
        this.hasEnded = hasEnded;
        this.startTime = startTime;
        this.endTime = endTime;
        this.score = score;
    }

    /**
     * Getter for quiz id
     * @return quiz id
     */
    public Long getQuizId() {
        return quizId;
    }

    /**
     * Setter for quiz id
     * @param quizId quiz id
     */
    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    /**
     * Getter for quiz owner username
     * @return quiz owner username
     */
    public String getOwnerUsername() {
        return ownerUsername;
    }

    /**
     * Setter for quiz owner username
     * @param ownerUsername quiz owner username
     */
    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    /**
     * Getter for boolean value indicating if the quiz has ended
     * @return boolean value indicating if the quiz has ended
     */
    public Boolean getHasEnded() {
        return hasEnded;
    }

    /**
     * Setter for boolean value indicating if the quiz has ended
     * @param hasEnded boolean value indicating if the quiz has ended
     */
    public void setHasEnded(Boolean hasEnded) {
        this.hasEnded = hasEnded;
    }

    /**
     * Getter for quiz start time
     * @return quiz start time
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Setter for quiz start time
     * @param startTime quiz start time
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter for quiz end time
     * @return quiz end time
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Setter for quiz end time
     * @param endTime quiz end time
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Getter for quiz score
     * @return quiz score
     */
    public Float getScore() {
        return score;
    }

    /**
     * Setter for quiz score
     * @param score quiz score
     */
    public void setScore(Float score) {
        this.score = score;
    }
}
