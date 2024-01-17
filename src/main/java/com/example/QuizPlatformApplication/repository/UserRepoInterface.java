package com.example.QuizPlatformApplication.repository;

import com.example.QuizPlatformApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing User entities in the Quiz Platform Application.
 * Extends JpaRepository to provide basic CRUD operations for User entities.
 */
@Repository
public interface UserRepoInterface extends JpaRepository<User ,Long> {
    /**
     * Find User by username.
     * @param username of the user.
     * @return User object or null if not exist with that username.
     */
    User findByUsername(String username);
}
