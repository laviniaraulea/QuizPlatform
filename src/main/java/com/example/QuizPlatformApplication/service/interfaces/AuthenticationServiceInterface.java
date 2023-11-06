package com.example.QuizPlatformApplication.service.interfaces;

import com.example.QuizPlatformApplication.model.User;
import org.springframework.stereotype.Service;


public interface AuthenticationServiceInterface {
    boolean logIn(User user);
}
