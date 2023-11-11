package com.example.QuizPlatformApplication.repository;

import com.example.QuizPlatformApplication.model.Quiz;
import com.example.QuizPlatformApplication.model.QuizProgress;
import com.example.QuizPlatformApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizProgressRepoInterface extends JpaRepository<QuizProgress, Long> {
    List<QuizProgress> findByQuizAndUser(Quiz quiz, User user);
}
