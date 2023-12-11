package com.example.QuizPlatformApplication.controller.dto;

import java.io.Serializable;

public class UsernameCategoryDTO implements Serializable {
    private String username;
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