package com.example.QuizPlatformApplication.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a quiz in the Quiz Platform Application.
 */
@Entity
@Data
@Table(name = "quizzes")
public class Quiz implements Serializable {
    /**
     * Unique identifier for the quiz.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * List of quiz entries associated with this quiz.
     */
    @OneToMany(mappedBy = "quiz")
    @JsonManagedReference
    private List<QuizEntry> quizEntries;

    /**
     * Owner or creator of the quiz.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    /**
     * Category of the quiz, e.g., Science, History, etc.
     */
    @Enumerated(EnumType.STRING)
    private QuizCategory category;

    /**
     * Difficulty level of the quiz, e.g., Easy, Medium, Hard.
     */
    @Enumerated(EnumType.STRING)
    private QuizDifficulty difficulty;

    /**
     * Time limit for completing the quiz in seconds.
     */
    @Column
    private int timeLimit;

    /**
     * Title or name of the quiz.
     */
    @Column
    private String title;

    /**
     * Description or instructions for the quiz.
     */
    @Column
    private String description;

    /**
     * Flag indicating whether participants can see their quiz results after completion.
     */
    @Column
    private boolean canSeeResult;

    /**
     *  Passing score required to successfully complete the quiz.
     */
    @Column
    private float passingScore;

    /**
     * Flag indicating whether a minimum score is required to pass the quiz.
     */
    @Column
    private boolean minimumScoreRequired;

    /**
     * Default constructor for the Quiz class.
     */
    public Quiz() {
    }

    /**
     * Parameterized constructor to create a new quiz.
     * @param owner The owner or creator of the quiz.
     * @param category The category of the quiz.
     * @param difficulty The difficulty level of the quiz.
     * @param timeLimit The time limit for completing the quiz.
     * @param description The description or instructions for the quiz.
     * @param canSeeResult Flag indicating whether participants can see their quiz results.
     * @param passingScore The passing score required to successfully complete the quiz.
     * @param minimumScoreRequired Flag indicating whether a minimum score is required to pass the quiz.
     * @param title The title or name of the quiz.
     */
    public Quiz(User owner, QuizCategory category, QuizDifficulty difficulty, int timeLimit, String description, boolean canSeeResult, float passingScore, boolean minimumScoreRequired, String title) {
        this.owner = owner;
        this.category = category;
        this.difficulty = difficulty;
        this.timeLimit = timeLimit;
        this.description = description;
        this.canSeeResult = canSeeResult;
        this.passingScore = passingScore;
        this.minimumScoreRequired = minimumScoreRequired;
        this.quizEntries = new ArrayList<>();
        this.title = title;
    }
}
