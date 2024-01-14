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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private QuizProgress quizProgress;

    @ManyToOne
    private QuizEntry quizEntry;

    @ManyToMany
    private List<QuizOptions> answers;

    public QuizUserAnswer() {
    }
}
