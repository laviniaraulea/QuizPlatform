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

    /**
     * Constructor
     */
    public UsernameCategoryDTO() {
    }

    /**
     * Constructor
     * @param username username of the user
     * @param category category of the quiz
     */
    public UsernameCategoryDTO(String username, String category) {
        this.username = username;
        this.category = category;
    }

    /**
     * Getter for username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for category
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Setter for category
     * @param category category
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
