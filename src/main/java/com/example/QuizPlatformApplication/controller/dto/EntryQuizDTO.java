package com.example.QuizPlatformApplication.controller.dto;

public class EntryQuizDTO {
    private Long quizEntryId;
    private Long quizId;

    public EntryQuizDTO(Long quizEntryId, Long quizId) {
        this.quizEntryId = quizEntryId;
        this.quizId = quizId;
    }

    public Long getQuizEntryId() {
        return quizEntryId;
    }

    public void setQuizEntryId(Long quizEntryId) {
        this.quizEntryId = quizEntryId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }
}
