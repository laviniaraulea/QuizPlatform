package com.example.QuizPlatformApplication.service.interfaces;

import com.example.QuizPlatformApplication.model.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> getAllUsers();

    User getUserByUsername(String username);
}
