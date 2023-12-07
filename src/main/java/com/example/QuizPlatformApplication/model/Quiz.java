package com.example.QuizPlatformApplication.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "quizzes")
public class Quiz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "quiz")
    @JsonManagedReference
    private List<QuizEntry> quizEntries;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Enumerated(EnumType.STRING)
    private QuizCategory category;

    @Enumerated(EnumType.STRING)
    private QuizDifficulty difficulty;

    @Column
    private int timeLimit;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private boolean canSeeResult;

    @Column
    private float passingScore;

    @Column
    private boolean minimumScoreRequired;

    public Quiz() {
    }

    public Quiz(User owner, QuizCategory category, QuizDifficulty difficulty, int timeLimit, String description, boolean canSeeResult, float passingScore, boolean minimumScoreRequired) {
        this.owner = owner;
        this.category = category;
        this.difficulty = difficulty;
        this.timeLimit = timeLimit;
        this.description = description;
        this.canSeeResult = canSeeResult;
        this.passingScore = passingScore;
        this.minimumScoreRequired = minimumScoreRequired;
        this.quizEntries = new ArrayList<>();
    }
}
