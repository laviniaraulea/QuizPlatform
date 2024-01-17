package com.example.QuizPlatformApplication.controller.dto;

import java.io.Serializable;

/**
 * DTO class for username and category answers
 */
public class UsernameCategoryDTO implements Serializable {
    /**
     * username of the user as string
     */
    private String username;

    /**
     * category of the quiz as string
     */
    private String category;

    public UsernameCategoryDTO() {
    }

    public UsernameCategoryDTO(String username, String category) {
        this.username = username;
        this.category = category;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}