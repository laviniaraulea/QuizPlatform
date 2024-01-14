package com.example.QuizPlatformApplication.controller.dto;

/**
 * DTO which contains the id of a quiz and the id of a question
 */
public class EntryQuizDTO {
    /**
     * The id of a question.
     */
    private Long quizEntryId;
    /**
     * The id of a quiz.
     */
    private Long quizId;

    /**
     * Constructs a new EntryQuizDTO with the specified parameters.
     * @param quizEntryId : id of the question
     * @param quizId : id of the quiz
     */
    public EntryQuizDTO(Long quizEntryId, Long quizId) {
        this.quizEntryId = quizEntryId;
        this.quizId = quizId;
    }

    /**
     * Gets the quiz entry id of the EntryQuizDTO.
     * @return : the quiz entry id of the EntryQuizDTO.
     */
    public Long getQuizEntryId() {
        return quizEntryId;
    }

    /**
     * Sets the quizEntryId of EntryQuizDTO
     * This method updates the quizEntryId attribute of the QuizEntryDTO instance.
     * @param quizEntryId : the new quizEntryId to be set for the QuizEntryDTO
     */
    public void setQuizEntryId(Long quizEntryId) {
        this.quizEntryId = quizEntryId;
    }

    /**
     * Gets the quiz id of the EntryQuizDTO.
     * @return : the quiz id of the EntryQuizDTO.
     */
    public Long getQuizId() {
        return quizId;
    }

    /**
     * Sets the quizId of EntryQuizDTO
     * This method updates the quizId attribute of the QuizEntryDTO instance.
     * @param quizId : the new quizId to be set for the QuizEntryDTO
     */
    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }
}
