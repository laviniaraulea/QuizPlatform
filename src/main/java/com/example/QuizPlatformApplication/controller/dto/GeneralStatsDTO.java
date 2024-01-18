package com.example.QuizPlatformApplication.controller.dto;

import java.io.Serializable;

/**
 * DTO class for general stats for a user
 */
public class GeneralStatsDTO implements Serializable {
    /**
     * number of total quizzes created by the user as long
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

    /**
     * Constructor
     * @param totalQuizzesCreated number of total quizzes created by the user
     * @param totalQuizzesDone number of total quizzes done by the user
     * @param averageScoreAllQuizzes average score of a user
     */
    public GeneralStatsDTO(long totalQuizzesCreated, long totalQuizzesDone, float averageScoreAllQuizzes) {
        this.totalQuizzesCreated = totalQuizzesCreated;
        this.totalQuizzesDone = totalQuizzesDone;
        this.averageScoreQuizzes = averageScoreAllQuizzes;
    }

    /**
     * Constructor
     */
    public GeneralStatsDTO() {}

    /**
     * Getter for number of total quizzes created by the user
     * @return number of total quizzes created by the user
     */
    public long getTotalQuizzesCreated() {
        return totalQuizzesCreated;
    }

    /**
     * Setter for number of total quizzes created by the user
     * @param totalQuizzesCreated number of total quizzes created by the user
     */
    public void setTotalQuizzesCreated(long totalQuizzesCreated) {
        this.totalQuizzesCreated = totalQuizzesCreated;
    }

    /**
     * Getter for number of total quizzes done by the user
     * @return number of total quizzes done by the user
     */
    public long getTotalQuizzesDone() {
        return totalQuizzesDone;
    }

    /**
     * Setter for number of total quizzes done by the user
     * @param totalQuizzesDone number of total quizzes done by the user
     */
    public void setTotalQuizzesDone(long totalQuizzesDone) {
        this.totalQuizzesDone = totalQuizzesDone;
    }

    /**
     * Getter for average score of a user
     * @return average score of a user
     */
    public float getAverageScoreAllQuizzes() {
        return averageScoreQuizzes;
    }

    /**
     * Setter for average score of a user
     * @param averageScoreAllQuizzes average score of a user
     */
    public void setAverageScoreAllQuizzes(float averageScoreAllQuizzes) {
        this.averageScoreQuizzes = averageScoreAllQuizzes;
    }
}
