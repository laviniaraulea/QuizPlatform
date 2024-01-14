package com.example.QuizPlatformApplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Entity class representing a quiz entry/question in the Quiz Platform Application.
 */
@Entity
@Data
@Table(name = "quizEntries")
public class QuizEntry implements Serializable {
    /**
     * Unique identifier for the quiz entry.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Reference to the parent quiz to which this entry belongs.
     */
    @ManyToOne
    @JoinColumn(name = "quizId")
    @JsonBackReference
    private Quiz quiz;

    /**
     * The question text for the quiz entry.
     */
    @Column
    private String question;

    /**
     * List of options and explanations associated with the quiz entry.
     */
    @OneToMany(mappedBy = "quizEntry")
    @JsonManagedReference
    private List<QuizOptions> optionAndExplanation;

    /**
     * Flag indicating whether the quiz entry has multiple correct answers.
     */
    @Column
    private boolean isMultipleChoice;

    /**
     * Hint or additional information for the quiz entry.
     */
    @Column
    private String hint;

    /**
     * Default constructor for the QuizEntry class.
     */
    public QuizEntry() {
    }

    /**
     * Parameterized constructor to create a new quiz entry/question.
     * @param quiz The quiz to which this entry belongs.
     * @param question The question text for the quiz entry.
     * @param optionAndExplanation List of options and explanations associated with the quiz entry.
     * @param isMultipleChoice Flag indicating whether the quiz entry has multiple correct answers.
     * @param hint Hint or additional information for the quiz entry.
     */
    public QuizEntry(Quiz quiz, String question, List<QuizOptions> optionAndExplanation, boolean isMultipleChoice, String hint) {
        this.quiz = quiz;
        this.question = question;
        this.optionAndExplanation = optionAndExplanation;
        this.isMultipleChoice = isMultipleChoice;
        this.hint = hint;
    }

    /**
     * String representation of the isMultipleChoice flag for JSON serialization.
     */
    @JsonProperty("isMultipleChoice")
    private String isMultipleChoiceStr;

    /**
     * Gets the boolean value of the isMultipleChoice flag.
     * @return The boolean value of the isMultipleChoice flag.
     */
    public boolean isMultipleChoice() {
        return Boolean.parseBoolean(isMultipleChoiceStr);
    }
}
