package com.example.QuizPlatformApplication.service.implementation;

import com.example.QuizPlatformApplication.model.User;
import com.example.QuizPlatformApplication.repository.UserRepoInterface;
import com.example.QuizPlatformApplication.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserServiceInterface {
    @Autowired
    private UserRepoInterface userRepoInterface;
    @Override
    public List<User> getAllUsers() {
        return userRepoInterface.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepoInterface.findByUsername(username);
    }
}
