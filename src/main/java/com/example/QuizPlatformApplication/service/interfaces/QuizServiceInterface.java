package com.example.QuizPlatformApplication.service.interfaces;



import com.example.QuizPlatformApplication.model.Quiz;
import com.example.QuizPlatformApplication.model.QuizEntry;
import com.example.QuizPlatformApplication.service.ServiceException;

import java.util.List;

public interface QuizServiceInterface {
    void createQuiz(Quiz quiz) throws ServiceException;
    void createQuestion(QuizEntry quizEntry) throws ServiceException;
    void addQuestionToQuiz(Quiz quiz, QuizEntry quizEntry) throws ServiceException;
    void deleteQuestionFromQuiz(Quiz quiz, QuizEntry quizEntry) throws ServiceException;

    Quiz getQuizById(Long id) throws ServiceException;

    QuizEntry getQuizEntryById(Long id) throws ServiceException;

    List<Quiz> getAllQuizzes();
}
