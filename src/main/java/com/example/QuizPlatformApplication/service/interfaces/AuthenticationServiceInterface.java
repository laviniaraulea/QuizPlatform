package com.example.QuizPlatformApplication.service.interfaces;

import com.example.QuizPlatformApplication.model.User;
import com.example.QuizPlatformApplication.service.ServiceException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Interface used for authenticating the user or signing up a new user*/
public interface AuthenticationServiceInterface {
    /**
     *  Function to check if a user exists in the database
     * @param user The user who wants log in the application, has to have username and password
     * @return true the username and password match with the ones from the database
     *         false else
     */
    boolean logIn(User user);

    /**
     * Function to add a new user in the database
     * @param username the username of the new user
     * @param password the password for the new user
     * @param dateOfBirth date of birth for the new user
     * @throws ServiceException if username already exists or the username contains something beside letters, numbers and '_'
     */
    void signUp(String username, String password, LocalDate dateOfBirth) throws ServiceException;
}
