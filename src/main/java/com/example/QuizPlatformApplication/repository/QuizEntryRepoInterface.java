package com.example.QuizPlatformApplication.repository;

import com.example.QuizPlatformApplication.model.QuizEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing QuizEntry entities in the Quiz Platform Application.
 * Extends JpaRepository to provide basic CRUD operations for QuizEntry entities.
 */
@Repository
public interface QuizEntryRepoInterface extends JpaRepository<QuizEntry, Long> {
    // No additional methods are added in this interface, as it extends JpaRepository.
    // JpaRepository provides methods for basic CRUD operations for QuizEntry entities.
}
