package com.example.QuizPlatformApplication.service.interfaces;



import com.example.QuizPlatformApplication.controller.dto.AnswerDTO;
import com.example.QuizPlatformApplication.controller.dto.FailedAndPassDTO;
import com.example.QuizPlatformApplication.controller.dto.GeneralStatsDTO;
import com.example.QuizPlatformApplication.model.Quiz;
import com.example.QuizPlatformApplication.model.QuizEntry;
import com.example.QuizPlatformApplication.model.QuizProgress;
import com.example.QuizPlatformApplication.model.User;
import com.example.QuizPlatformApplication.model.validator.MyException;
import com.example.QuizPlatformApplication.model.validator.Validator;
import com.example.QuizPlatformApplication.service.ServiceException;

import java.util.List;

public interface QuizServiceInterface {
    void createQuiz(Quiz quiz) throws ServiceException, MyException;
    void createQuestion(QuizEntry quizEntry) throws ServiceException, MyException;
    void addQuestionToQuiz(Quiz quiz, QuizEntry quizEntry) throws ServiceException, MyException;
    void deleteQuestionFromQuiz(Quiz quiz, QuizEntry quizEntry) throws ServiceException;

    Quiz getQuizById(Long id) throws ServiceException;

    QuizEntry getQuizEntryById(Long id) throws ServiceException;

    List<Quiz> getAllQuizzes();

    List<Quiz> getQuizzesByCategory(String category);
    List<Quiz> getQuizzesByDifficulty(String difficulty);

    boolean isQuizStarted(Quiz quiz, User user);

    QuizProgress getLastQuizProgress(Quiz quiz, User user);

    void startQuiz(Quiz quiz, User user) throws ServiceException;

    void endQuiz(Quiz quiz, User user, List<AnswerDTO> userAnswers) throws ServiceException;

    GeneralStatsDTO getGeneralStats(User user);
    GeneralStatsDTO getStatsPerCategory(User user, String category);
    FailedAndPassDTO getFailedAndPass(User user);
}
