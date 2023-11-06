package com.example.QuizPlatformApplication.repository;

import com.example.QuizPlatformApplication.model.QuizOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizOptionRepoInterface extends JpaRepository<QuizOptions, Long> {

}
