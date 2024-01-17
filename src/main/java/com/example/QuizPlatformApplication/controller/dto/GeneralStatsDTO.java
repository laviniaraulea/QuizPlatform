package com.example.QuizPlatformApplication.controller.dto;

import java.io.Serializable;

/**
 * DTO class for general stats for a user
 */
public class GeneralStatsDTO implements Serializable {
    /**
     * number of total quizzes creaded by the user as long
     */
    private long totalQuizzesCreated;

    /**
     * number of total quizzes done by the user as long
     */
    private long totalQuizzesDone;

    /**
     * average score of a user as float
     */
    private float averageScoreQuizzes;

    public GeneralStatsDTO(long totalQuizzesCreated, long totalQuizzesDone, float averageScoreAllQuizzes) {
        this.totalQuizzesCreated = totalQuizzesCreated;
        this.totalQuizzesDone = totalQuizzesDone;
        this.averageScoreQuizzes = averageScoreAllQuizzes;
    }

    public GeneralStatsDTO() {}

    public long getTotalQuizzesCreated() {
        return totalQuizzesCreated;
    }

    public void setTotalQuizzesCreated(long totalQuizzesCreated) {
        this.totalQuizzesCreated = totalQuizzesCreated;
    }

    public long getTotalQuizzesDone() {
        return totalQuizzesDone;
    }

    public void setTotalQuizzesDone(long totalQuizzesDone) {
        this.totalQuizzesDone = totalQuizzesDone;
    }

    public float getAverageScoreAllQuizzes() {
        return averageScoreQuizzes;
    }

    public void setAverageScoreAllQuizzes(float averageScoreAllQuizzes) {
        this.averageScoreQuizzes = averageScoreAllQuizzes;
    }
}
