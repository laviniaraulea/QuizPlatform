package com.example.QuizPlatformApplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "quizOptions")
public class QuizOptions implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quizEntryId")
    @JsonBackReference
    private QuizEntry quizEntry;

    @Column
    private String option;

    @Column
    private String explanation;

    @Column
    private boolean isCorrectOption;

    public QuizOptions() {
    }
}
