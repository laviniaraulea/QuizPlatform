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

    public FailedAndPassDTO(long noFailedQuizzes, long noPassQuizzes) {
        this.noFailedQuizzes = noFailedQuizzes;
        this.noPassQuizzes = noPassQuizzes;
    }

    public long getNoFailedQuizzes() {
        return noFailedQuizzes;
    }

    public void setNoFailedQuizzes(long noFailedQuizzes) {
        this.noFailedQuizzes = noFailedQuizzes;
    }

    public long getNoPassQuizzes() {
        return noPassQuizzes;
    }

    public void setNoPassQuizzes(long noPassQuizzes) {
        this.noPassQuizzes = noPassQuizzes;
    }
}

