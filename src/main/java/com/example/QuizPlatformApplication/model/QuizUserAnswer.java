package com.example.QuizPlatformApplication.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Entity class for the answer in a try of a quiz
 */
@Entity
@Data
@Table
public class QuizUserAnswer implements Serializable {
    /**
     * Unique identifier for the answer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Reference to the progress to which this answear belongs.
     */
    @ManyToOne
    private QuizProgress quizProgress;

    /**
     * Reference to the Quiz entry to which this answear belongs.
     */
    @ManyToOne
    private QuizEntry quizEntry;

    /**
     * List of user's answers for a quiz.
     */
    @ManyToMany
    private List<QuizOptions> answers;

    public QuizUserAnswer() {
    }
}
