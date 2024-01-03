package com.example.QuizPlatformApplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("isCorrectOption")
    private String isMultipleChoiceStr;

    public boolean isCorrectOption() {
        return Boolean.parseBoolean(isMultipleChoiceStr);
    }

    @Column
    private boolean isCorrectOption;

    public QuizOptions(Long id, QuizEntry quizEntry, String option, String explanation, boolean isCorrectOption) {
        this.id = id;
        this.quizEntry = quizEntry;
        this.option = option;
        this.explanation = explanation;
        this.isCorrectOption = isCorrectOption;
    }

    public QuizOptions() {
    }
}
