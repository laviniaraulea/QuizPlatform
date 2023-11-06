package com.example.QuizPlatformApplication.service;

public class ServiceException extends Exception {
    public ServiceException(String invalidUsernameOrPassword) {
        super(invalidUsernameOrPassword);
    }
}
