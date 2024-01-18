package com.example.QuizPlatformApplication.controller.dto;

import java.io.Serializable;

/**
 * DTO class for no of failed and passed quizzes
 */
public class FailedAndPassDTO implements Serializable {
    /**
     * number of failed quizzes as long
     */
    private long noFailedQuizzes;

    /**
     * number of passed quizzes as long
     */
    private long noPassQuizzes;

    /**
     * Constructor
     * @param noFailedQuizzes number of failed quizzes
     * @param noPassQuizzes number of passed quizzes
     */
    public FailedAndPassDTO(long noFailedQuizzes, long noPassQuizzes) {
        this.noFailedQuizzes = noFailedQuizzes;
        this.noPassQuizzes = noPassQuizzes;
    }

    /**
     * Getter for number of failed quizzes
     * @return number of failed quizzes
     */
    public long getNoFailedQuizzes() {
        return noFailedQuizzes;
    }

    /**
     * Setter for number of failed quizzes
     * @param noFailedQuizzes number of failed quizzes
     */
    public void setNoFailedQuizzes(long noFailedQuizzes) {
        this.noFailedQuizzes = noFailedQuizzes;
    }

    /**
     * Getter for number of passed quizzes
     * @return number of passed quizzes
     */
    public long getNoPassQuizzes() {
        return noPassQuizzes;
    }

    /**
     * Setter for number of passed quizzes
     * @param noPassQuizzes number of passed quizzes
     */
    public void setNoPassQuizzes(long noPassQuizzes) {
        this.noPassQuizzes = noPassQuizzes;
    }
}
