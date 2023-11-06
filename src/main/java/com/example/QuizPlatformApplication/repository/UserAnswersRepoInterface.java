package com.example.QuizPlatformApplication.repository;

import com.example.QuizPlatformApplication.model.UserAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnswersRepoInterface extends JpaRepository<UserAnswers, Long> {

}
