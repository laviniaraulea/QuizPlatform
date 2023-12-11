package com.example.QuizPlatformApplication.controller.dto;

import java.io.Serializable;

public class FailedAndPassDTO implements Serializable {
    private long noFailedQuizzes;
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

