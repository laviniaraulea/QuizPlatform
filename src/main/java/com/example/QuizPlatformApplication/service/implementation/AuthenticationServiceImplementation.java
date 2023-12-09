package com.example.QuizPlatformApplication.service.implementation;

import com.example.QuizPlatformApplication.model.User;
import com.example.QuizPlatformApplication.service.ServiceException;
import com.example.QuizPlatformApplication.service.interfaces.AuthenticationServiceInterface;
import com.example.QuizPlatformApplication.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class AuthenticationServiceImplementation implements AuthenticationServiceInterface {
    @Autowired
    private UserServiceInterface userService;

    @Override
    public boolean logIn(User user) {
        User dbUser = userService.getUserByUsername(user.getUsername());
        if(dbUser == null){
            return false;
        }
        return Objects.equals(dbUser.getPassword(), user.getPassword());
    }

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
