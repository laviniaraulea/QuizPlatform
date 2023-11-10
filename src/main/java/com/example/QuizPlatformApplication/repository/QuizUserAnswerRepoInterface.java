package com.example.QuizPlatformApplication.repository;

import com.example.QuizPlatformApplication.model.QuizUserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizUserAnswerRepoInterface extends JpaRepository<QuizUserAnswer, Long> {
}
