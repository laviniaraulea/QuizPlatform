package com.example.QuizPlatformApplication.controller.dto;

import com.example.QuizPlatformApplication.model.QuizCategory;
import com.example.QuizPlatformApplication.model.QuizDifficulty;
import com.example.QuizPlatformApplication.model.QuizEntry;

import java.util.List;

public class QuizDTO {
    // quiz-ul care contine username-ul user-ului in loc de user-ul propriu-zis, si nu are lista de intrebari
    private Long id;

    private String username_owner;

    private QuizCategory category;

    private QuizDifficulty difficulty;

    private int timeLimit;

    private String description;

    private boolean canSeeResult;

    private float passingScore;

    private boolean minimumScoreRequired;
    private String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername_owner() {
        return username_owner;
    }

    public void setUsername_owner(String username_owner) {
        this.username_owner = username_owner;
    }

    public QuizCategory getCategory() {
        return category;
    }

    public void setCategory(QuizCategory category) {
        this.category = category;
    }

    public QuizDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(QuizDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCanSeeResult() {
        return canSeeResult;
    }

    public void setCanSeeResult(boolean canSeeResult) {
        this.canSeeResult = canSeeResult;
    }

    public float getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(float passingScore) {
        this.passingScore = passingScore;
    }

    public boolean isMinimumScoreRequired() {
        return minimumScoreRequired;
    }

    public void setMinimumScoreRequired(boolean minimumScoreRequired) {
        this.minimumScoreRequired = minimumScoreRequired;
    }
}
