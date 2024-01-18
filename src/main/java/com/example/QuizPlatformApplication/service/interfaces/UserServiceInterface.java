package com.example.QuizPlatformApplication.service.interfaces;

import com.example.QuizPlatformApplication.model.User;

import java.time.LocalDate;
import java.util.List;

/**
 * Class used for operating on the user records from the database and doing CRUD operations with it
 */
public interface UserServiceInterface {
    /**
     * Function to get all the user records from the database
     * @return a list of users from the database
     */
    List<User> getAllUsers();

    /**
     * Function to get a user by its username parameter
     * @param username the username we search in the database
     * @return User and it's details from the database
     */
    User getUserByUsername(String username);

    /**
     * Function to add a new user in the database
     * @param username the username of the user
     * @param password the password of the user
     * @param dateOfBirth the date of birth of the user
     */
    void addUser(String username, String password, LocalDate dateOfBirth);
}
