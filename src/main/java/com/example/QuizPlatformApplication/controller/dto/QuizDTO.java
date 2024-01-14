package com.example.QuizPlatformApplication.controller.dto;

import com.example.QuizPlatformApplication.model.QuizCategory;
import com.example.QuizPlatformApplication.model.QuizDifficulty;
import com.example.QuizPlatformApplication.model.QuizEntry;

import java.util.List;

/**
 * DTO representing a quiz
 * This class encapsulates the necessary information about a quiz for communication between the controller and the client
 */
public class QuizDTO {
    /**
     * Unique identifier for the quiz.
     */
    private Long id;

    /**
     * Username of the owner/creator of the quiz.
     */
    private String username_owner;

    /**
     * Category of the quiz, such as Science, History, etc.
     */
    private QuizCategory category;

    /**
     * Difficulty level of the quiz, e.g., Easy, Medium, Hard.
     */
    private QuizDifficulty difficulty;

    /**
     * Time limit for completing the quiz in seconds.
     */
    private int timeLimit;

    /**
     * Description or instructions for the quiz.
     */
    private String description;

    /**
     * Flag indicating whether participants can see their quiz results after completion.
     */
    private boolean canSeeResult;

    /**
     * Passing score required to successfully complete the quiz.
     */
    private float passingScore;

    /**
     * Flag indicating whether a minimum score is required to pass the quiz.
     */
    private boolean minimumScoreRequired;
    /**
     * Title or name of the quiz.
     */
    private String title;

    /**
     *  Constructs a new QuizDTO with the specified parameters.
     * @param id  Unique identifier for the quiz.
     * @param username_owner Username of the owner/creator of the quiz.
     * @param category  Category of the quiz.
     * @param difficulty Difficulty level of the quiz.
     * @param timeLimit Time limit for completing the quiz.
     * @param description Description or instructions for the quiz.
     * @param canSeeResult Flag indicating whether participants can see their quiz results.
     * @param passingScore Passing score required to successfully complete the quiz.
     * @param minimumScoreRequired Flag indicating whether a minimum score is required to pass the quiz.
     * @param title Title or name of the quiz.
     */
    public QuizDTO(Long id, String username_owner, QuizCategory category, QuizDifficulty difficulty, int timeLimit, String description, boolean canSeeResult, float passingScore, boolean minimumScoreRequired, String title) {
        this.id = id;
        this.username_owner = username_owner;
        this.category = category;
        this.difficulty = difficulty;
        this.timeLimit = timeLimit;
        this.description = description;
        this.canSeeResult = canSeeResult;
        this.passingScore = passingScore;
        this.minimumScoreRequired = minimumScoreRequired;
        this.title = title;
    }

    /**
     * Gets the title of the quiz.
     * @return The title of the quiz.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the id of the quiz.
     * @return The id of the quiz.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the quiz.
     * This method updates the id attribute of the QuizDTO instance.
     *
     * @param id The new id to be set for the quiz
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the owner of the quiz.
     * @return The name of the owner of the quiz.
     */
    public String getUsername_owner() {
        return username_owner;
    }

    /**
     * Gets the category of the quiz.
     * @return The category of the quiz.
     */
    public QuizCategory getCategory() {
        return category;
    }

    /**
     * Sets the category of the quiz.
     * This method updates the category attribute of the QuizDTO instance.
     *
     * @param category The new category to be set for the quiz.
     */
    public void setCategory(QuizCategory category) {
        this.category = category;
    }

    /**
     * Gets the difficulty of the quiz.
     * @return The difficulty of the quiz.
     */
    public QuizDifficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the difficulty of the quiz.
     * This method updates the difficulty attribute of the QuizDTO instance.
     * @param difficulty The new difficulty to be set for the quiz.
     */
    public void setDifficulty(QuizDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Gets the time limit of the quiz.
     * @return The time limit of the quiz
     */
    public int getTimeLimit() {
        return timeLimit;
    }

    /**
     * Sets the time limit of the quiz.
     * This method updates the time limit attribute of the QuizDTO instance.
     * @param timeLimit The new time limit to be set for the quiz.
     */
    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    /**
     * Gets the description of the quiz.
     * @return The description of the quiz.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the quiz.
     * This method updates the description attribute of the QuizDTO instance.
     * @param description The new description to be set for the quiz.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the canSeeResult field of the quiz.
     * @return The canSeeResult field of the quiz
     */
    public boolean isCanSeeResult() {
        return canSeeResult;
    }

    /**
     * Sets the canSeeResult of the quiz.
     * This method updates the canSeeResult attribute of the QuizDTO instance.
     * @param canSeeResult The new canSeeResult to be set to the quiz.
     */
    public void setCanSeeResult(boolean canSeeResult) {
        this.canSeeResult = canSeeResult;
    }

    /**
     * Gets the passing score of the quiz.
     * @return The passing score of the quiz.
     */
    public float getPassingScore() {
        return passingScore;
    }

    /**
     * Sets the passingScore for the quiz.
     * This method updates the passing score attribute of the QuizDTO instance.
     * @param passingScore The new passing score to be set for the quiz.
     */
    public void setPassingScore(float passingScore) {
        this.passingScore = passingScore;
    }

    /**
     * Gets the minimumScoreRequired field of the quiz.
     * @return The minimumScoreRequired field of the quiz.
     */
    public boolean isMinimumScoreRequired() {
        return minimumScoreRequired;
    }

    /**
     * Sets the minimum score required for the quiz.
     * This method updates the minimum score required attribute of the QuizDTO instance.
     * @param minimumScoreRequired The new minimum passing score required to be set for the quiz.
     */
    public void setMinimumScoreRequired(boolean minimumScoreRequired) {
        this.minimumScoreRequired = minimumScoreRequired;
    }
}
