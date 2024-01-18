package com.example.QuizPlatformApplication.controller.dto;

/**
 * DTO class for user
 */
public class UserDTO {
    /**
     * Username
     */
    private String username;
    /**
     * Password
     */
    private String password;
    /**
     * Date of birth
     */
    private String dateOfBirth;

    /**
     * Constructor
     */
    public UserDTO() {
    }

    /**
     * Constructor
     * @param username username of the user
     * @param password password of the user
     * @param dateOfBirth date of birth of the user
     */
    public UserDTO(String username, String password, String dateOfBirth) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
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
     * Getter for password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for date of birth
     * @return date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Setter for date of birth
     * @param dateOfBirth date of birth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
