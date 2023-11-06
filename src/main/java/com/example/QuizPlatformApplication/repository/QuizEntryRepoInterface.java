package com.example.QuizPlatformApplication.repository;

import com.example.QuizPlatformApplication.model.QuizEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizEntryRepoInterface extends JpaRepository<QuizEntry, Long> {

}
