package com.example.QuizPlatformApplication.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entity class representing progress in the Quiz Platform Application.
 */
@Entity
@Data
@Table
public class QuizProgress implements Serializable {
    /**
     * Unique identifier for the progress.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Reference to the quiz.
     */
    @ManyToOne
    private Quiz quiz;

    /**
     * Reference to the user who take the quiz.
     */
    @ManyToOne
    private User user;

    /**
     * Boolean flag indicating whether the quiz is ended or not.
     */
    @Column
    private Boolean hasEnded;

    /**
     * Indicate the start date and time of the quiz.
     */
    @Column
    private LocalDateTime startTime;

    /**
     * Indicate the end date and time of the quiz.
     */
    @Column
    private LocalDateTime endTime;

    /**
     * Score the user received on the quiz
     */
    @Column
    private Float score;

    public QuizProgress() {
    }
}
