package com.example.QuizPlatformApplication.service.interfaces;

import com.example.QuizPlatformApplication.model.User;
import com.example.QuizPlatformApplication.service.ServiceException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


public interface AuthenticationServiceInterface {
    boolean logIn(User user);
    void signUp(String username, String password, LocalDate dateOfBirth) throws ServiceException;
}
