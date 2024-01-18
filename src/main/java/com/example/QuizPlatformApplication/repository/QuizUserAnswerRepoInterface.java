package com.example.QuizPlatformApplication.repository;

import com.example.QuizPlatformApplication.model.QuizUserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing QuizUserAnswer entities in the Quiz Platform Application.
 * Extends JpaRepository to provide basic CRUD operations for QuizUserAnswer entities.
 */
@Repository
public interface QuizUserAnswerRepoInterface extends JpaRepository<QuizUserAnswer, Long> {
    // No additional methods are added in this interface, as it extends JpaRepository.
    // JpaRepository provides methods for basic CRUD operations for QuizUserAnswer entities.
}
