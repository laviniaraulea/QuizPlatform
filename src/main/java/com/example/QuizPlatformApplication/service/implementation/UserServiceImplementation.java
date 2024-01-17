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

    @Override
    public void addUser(String username, String password, LocalDate dateOfBirth) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setDateOfBirth(dateOfBirth);
        userRepoInterface.save(user);
    }
}
