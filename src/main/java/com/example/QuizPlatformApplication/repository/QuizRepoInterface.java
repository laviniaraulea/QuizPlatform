package com.example.QuizPlatformApplication.repository;

import com.example.QuizPlatformApplication.model.Quiz;
import com.example.QuizPlatformApplication.model.QuizCategory;
import com.example.QuizPlatformApplication.model.QuizDifficulty;
import com.example.QuizPlatformApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Quiz entities in the Quiz Platform Application.
 * Extends JpaRepository to provide basic CRUD operations for Quiz entities.
 */
@Repository
public interface QuizRepoInterface extends JpaRepository<Quiz, Long> {
    /**
     * Find quizzes by quiz category.
     * @param category the quiz category.
     * @return List of Quiz objects.
     */
    List<Quiz> findAllByCategory(QuizCategory category);

    /**
     * Find quizzes by difficulty.
     * @param difficulty the quiz difficulty.
     * @return List of Quiz objects.
     */
    List<Quiz> findAllByDifficulty(QuizDifficulty difficulty);

    /**
     * Count all Quizzes by owner.
     * @param user it's the owner of the quiz.
     * @return no of quiz by owner.
     */
    long countAllByOwner(User user);

    /**
     * Count all Quizzes by owner and quiz category.
     * @param user it's the owner of the quiz.
     * @param category the quiz category.
     * @return no of quiz by owner and quiz category.
     */
    long countAllByOwnerAndCategory(User user, QuizCategory category);
}
