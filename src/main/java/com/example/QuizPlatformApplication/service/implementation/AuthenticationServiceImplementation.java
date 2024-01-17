package com.example.QuizPlatformApplication.service.implementation;

import com.example.QuizPlatformApplication.model.User;
import com.example.QuizPlatformApplication.service.ServiceException;
import com.example.QuizPlatformApplication.service.interfaces.AuthenticationServiceInterface;
import com.example.QuizPlatformApplication.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Implementation of the AuthenticationService class
 */
@Service
public class AuthenticationServiceImplementation implements AuthenticationServiceInterface {
    /**
     * Parameter use in order to access the records from the database
     */
    @Autowired
    private UserServiceInterface userService;

    /**
     *  Function to check if a user exists in the database
     * @param user The user who wants log in the application, has to have username and password
     * @return true the username and password match with the ones from the database
     *         false else
     */
    @Override
    public boolean logIn(User user) {
        User dbUser = userService.getUserByUsername(user.getUsername());
        if(dbUser == null){
            return false;
        }
        return Objects.equals(dbUser.getPassword(), user.getPassword());
    }
    /**
     * Function to add a new user in the database
     * @param username the username of the new user
     * @param password the password for the new user
     * @param dateOfBirth date of birth for the new user
     * @throws ServiceException if username already exists or the username contains something beside letters, numbers and '_'
     */
    @Override
    public void signUp(String username, String password, LocalDate dateOfBirth) throws ServiceException {
        User user = userService.getUserByUsername(username);
        if(user != null)
            throw new ServiceException("User already exists.");

        if(!username.matches("[a-zA-Z0-9_]+")){
            throw new ServiceException("Username can only contain letters, numbers and _.");
        }

        userService.addUser(username, password, dateOfBirth);
    }
}
