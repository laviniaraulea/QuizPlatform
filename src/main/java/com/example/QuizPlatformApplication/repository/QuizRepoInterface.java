package com.example.QuizPlatformApplication.repository;

import com.example.QuizPlatformApplication.model.Quiz;
import com.example.QuizPlatformApplication.model.QuizCategory;
import com.example.QuizPlatformApplication.model.QuizDifficulty;
import com.example.QuizPlatformApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepoInterface extends JpaRepository<Quiz, Long> {
    List<Quiz> findAllByCategory(QuizCategory category);

    List<Quiz> findAllByDifficulty(QuizDifficulty difficulty);

    long countAllByOwner(User user);

    long countAllByOwnerAndCategory(User user, QuizCategory category);
}
