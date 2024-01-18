package com.example.QuizPlatformApplication.repository;

import com.example.QuizPlatformApplication.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Profile entities in the Quiz Platform Application.
 * Extends JpaRepository to provide basic CRUD operations for Profile entities.
 */
@Repository
public interface ProfileRepoInterface extends JpaRepository<Profile, Long> {
    // No additional methods are added in this interface, as it extends JpaRepository.
    // JpaRepository provides methods for basic CRUD operations for Profile entities.

}
