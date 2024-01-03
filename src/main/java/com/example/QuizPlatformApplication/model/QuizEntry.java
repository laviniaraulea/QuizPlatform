package com.example.QuizPlatformApplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "quizEntries")
public class QuizEntry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quizId")
    @JsonBackReference
    private Quiz quiz;

    @Column
    private String question;

    @OneToMany(mappedBy = "quizEntry")
    @JsonManagedReference
    private List<QuizOptions> optionAndExplanation;

    @Column
    private boolean isMultipleChoice;

    @Column
    private String hint;

    public QuizEntry() {
    }

    public QuizEntry(Quiz quiz, String question, List<QuizOptions> optionAndExplanation, boolean isMultipleChoice, String hint) {
        this.quiz = quiz;
        this.question = question;
        this.optionAndExplanation = optionAndExplanation;
        this.isMultipleChoice = isMultipleChoice;
        this.hint = hint;
    }

    @JsonProperty("isMultipleChoice")
    private String isMultipleChoiceStr;

    public boolean isMultipleChoice() {
        return Boolean.parseBoolean(isMultipleChoiceStr);
    }
}
