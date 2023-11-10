package com.example.QuizPlatformApplication.repository;

import com.example.QuizPlatformApplication.model.QuizProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizProgressRepoInterface extends JpaRepository<QuizProgress, Long> {

}
