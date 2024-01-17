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

/**
 * Interface defining the contract for Quiz-related services.
 */
public interface QuizServiceInterface {
    /**
     * Creates a new quiz.
     * @param quiz The Quiz object to be created.
     * @throws MyException If validation of the quiz fails.
     */
    void createQuiz(Quiz quiz) throws MyException;

    /**
     * Creates a new quiz entry.
     * @param quizEntry The QuizEntry object representing the question to be created.
     * @throws MyException If validation of the question fails.
     */
    void createQuestion(QuizEntry quizEntry) throws MyException;

    /**
     * Adds a quiz entry to the specified quiz.
     * @param quiz The Quiz object to which the quiz entry will be added.
     * @param quizEntry The QuizEntry object representing the question to be added.
     * @throws MyException If validation of the quiz entry addition fails.
     */
    void addQuestionToQuiz(Quiz quiz, QuizEntry quizEntry) throws MyException;

    /**
     * Deletes a quiz entry from the specified quiz.
     * @param quiz The Quiz object from which the quiz entry will be deleted.
     * @param quizEntry The QuizEntry object representing the question to be deleted.
     * @throws ServiceException If an error occurs during the deletion process.
     */
    void deleteQuestionFromQuiz(Quiz quiz, QuizEntry quizEntry) throws ServiceException;

    /**
     * Retrieves a Quiz object by its ID.
     * @param id The ID of the quiz to retrieve.
     * @return The Quiz object.
     * @throws ServiceException If the quiz with the specified ID is not found.
     */
    Quiz getQuizById(Long id) throws ServiceException;

    /**
     * Retrieves a QuizEntry object by its ID.
     * @param id The ID of the quiz entry to retrieve.
     * @return The QuizEntry object.
     * @throws ServiceException If the quiz entry with the specified ID is not found.
     */
    QuizEntry getQuizEntryById(Long id) throws ServiceException;

    /**
     * Retrieves a list of all quizzes.
     * @return List of Quiz objects.
     */
    List<Quiz> getAllQuizzes();

    List<Quiz> getQuizzesByCategory(String category);
    List<Quiz> getQuizzesByDifficulty(String difficulty);

    boolean isQuizStarted(Quiz quiz, User user);

    QuizProgress getLastQuizProgress(Quiz quiz, User user);

    void startQuiz(Quiz quiz, User user) throws ServiceException;

    void endQuiz(Quiz quiz, User user, List<AnswerDTO> userAnswers) throws ServiceException;

    /**
     * Returns the general stats for a user
     * @param user the user
     * @return the total quizzes created, total quizzes done, average score of the user
     */
    GeneralStatsDTO getGeneralStats(User user);

    /**
     * Returns the stats per category for a user
     * @param user the user
     * @param category quiz category as string for stats
     * @return the stats for a specific catefory
     */
    GeneralStatsDTO getStatsPerCategory(User user, String category);

    /**
     * Returns the number of Failed Quizzes and the number of Paassed Quizzes for a user
     * @param user the user
     * @return  number of Failed Quizzes and number of Paassed Quizzes
     */
    FailedAndPassDTO getFailedAndPass(User user);
}
