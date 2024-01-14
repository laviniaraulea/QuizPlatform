package com.example.QuizPlatformApplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * Entity class representing options for a quiz entry/question in the Quiz Platform Application.
 */
@Entity
@Data
@Table(name = "quizOptions")
public class QuizOptions implements Serializable {
    /**
     * Unique identifier for the question option.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Reference to the quiz entry to which this option belongs.
     */
    @ManyToOne
    @JoinColumn(name = "quizEntryId")
    @JsonBackReference
    private QuizEntry quizEntry;

    /**
     * The text of the question option.
     */
    @Column
    private String option;

    /**
     * Explanation or additional information for the question option.
     */
    @Column
    private String explanation;

    /**
     * String representation of the isCorrectOption flag for JSON serialization.
     */
    @JsonProperty("isCorrectOption")
    private String isMultipleChoiceStr;

    /**
     * Gets the boolean value of the isCorrectOption flag.
     * @return The boolean value of the isCorrectOption flag.
     */
    public boolean isCorrectOption() {
        return Boolean.parseBoolean(isMultipleChoiceStr);
    }

    /**
     *  Boolean flag indicating whether the option is correct for the quiz entry.
     */
    @Column
    private boolean isCorrectOption;

    /**
     * Parameterized constructor to create a new question option.
     * @param id The unique identifier for the question option.
     * @param quizEntry The quiz entry to which this option belongs.
     * @param option The text of the question option.
     * @param explanation Explanation or additional information for the question option.
     * @param isCorrectOption Boolean flag indicating whether the option is correct for the quiz entry.
     */
    public QuizOptions(Long id, QuizEntry quizEntry, String option, String explanation, boolean isCorrectOption) {
        this.id = id;
        this.quizEntry = quizEntry;
        this.option = option;
        this.explanation = explanation;
        this.isCorrectOption = isCorrectOption;
    }

    /**
     * Default constructor for the QuizOptions class.
     */
    public QuizOptions() {
    }
}
