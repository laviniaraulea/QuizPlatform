package com.example.QuizPlatformApplication.service.implementation;

import com.example.QuizPlatformApplication.model.User;
import com.example.QuizPlatformApplication.repository.UserRepoInterface;
import com.example.QuizPlatformApplication.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the UserServiceInterface used in the app
 */
@Service
public class UserServiceImplementation implements UserServiceInterface {
    /**
     * The repository for managing User entities.
     */
    @Autowired
    private UserRepoInterface userRepoInterface;

    /**
     * Function to get all the user records from the database
     * @return an list of users from the database
     */
    @Override
    public List<User> getAllUsers() {
        return userRepoInterface.findAll();
    }

    /**
     * Function to get a user by it's username parameter
     * @param username the username we search in the database
     * @return User and it's details from the database
     */
    @Override
    public User getUserByUsername(String username) {
        return userRepoInterface.findByUsername(username);
    }

    /**
     * Function to add a new user in the database
     * @param username the username of the user
     * @param password the password of the user
     * @param dateOfBirth the date of birth of the user
     */
    @Override
    public void addUser(String username, String password, LocalDate dateOfBirth) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setDateOfBirth(dateOfBirth);
        userRepoInterface.save(user);
    }
}
