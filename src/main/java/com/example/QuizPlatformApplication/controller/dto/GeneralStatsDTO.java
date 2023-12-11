package com.example.QuizPlatformApplication.controller.dto;

import java.io.Serializable;

public class GeneralStatsDTO implements Serializable {
    private long totalQuizzesCreated;
    private long totalQuizzesDone;
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
