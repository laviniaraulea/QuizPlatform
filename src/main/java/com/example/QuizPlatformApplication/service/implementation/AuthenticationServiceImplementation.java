package com.example.QuizPlatformApplication.service.implementation;

import com.example.QuizPlatformApplication.model.User;
import com.example.QuizPlatformApplication.service.interfaces.AuthenticationServiceInterface;
import com.example.QuizPlatformApplication.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
