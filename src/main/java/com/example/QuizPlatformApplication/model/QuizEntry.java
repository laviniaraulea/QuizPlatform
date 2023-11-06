package com.example.QuizPlatformApplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

}