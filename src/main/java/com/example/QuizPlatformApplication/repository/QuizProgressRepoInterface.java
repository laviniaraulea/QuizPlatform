package com.example.QuizPlatformApplication.repository;

import com.example.QuizPlatformApplication.model.Quiz;
import com.example.QuizPlatformApplication.model.QuizCategory;
import com.example.QuizPlatformApplication.model.QuizProgress;
import com.example.QuizPlatformApplication.model.User;
import com.example.QuizPlatformApplication.model.validator.MyException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing QuizProgress entities in the Quiz Platform Application.
 * Extends JpaRepository to provide basic CRUD operations for QuizProgress entities.
 */
@Repository
public interface QuizProgressRepoInterface extends JpaRepository<QuizProgress, Long> {
    /**
     * Find QuizProgress by Quiz and User.
     * @param quiz the quiz.
     * @param user the user.
     * @return List of QuizzProgress objects.
     */
    List<QuizProgress> findByQuizAndUser(Quiz quiz, User user);

    /**
     * Count all QuizProgress by user and if are ended .
     * @param ended boolean if the quizProgress is ended or not.
     * @param user the user.
     * @return no of QuizProgress by user and if are ended.
     */
    long countAllByUserAndHasEnded(User user, boolean ended);

    /**
     * Count all QuizProgress by user, if are ended and by quiz category.
     * @param ended boolean if the quizProgress is ended or not.
     * @param user the user.
     * @param quizCategory the quizCategory.
     * @return no of QuizProgress by user, category and if are ended.
     */
    long countAllByUserAndHasEndedAndQuizCategory(User user, boolean ended, QuizCategory quizCategory);

    /**
     * Find QuizProgress by User and if are ended.
     * @param ended boolean if the quizProgress is ended or no.
     * @param user the user.
     * @return List of QuizzProgress objects.
     */
    List<QuizProgress> findByUserAndHasEnded(User user, boolean ended);

    /**
     * Find QuizProgress by User, QuizCategory and if are ended.
     * @param category the quiz category.
     * @param user the user.
     * @param ended boolean if the quizProgress is ended or no.
     * @return List of QuizzProgress objects.
     */
    List<QuizProgress> findByUserAndHasEndedAndQuizCategory(User user, boolean ended, QuizCategory category);
}
