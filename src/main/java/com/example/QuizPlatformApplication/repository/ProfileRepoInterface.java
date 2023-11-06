package com.example.QuizPlatformApplication.repository;

import com.example.QuizPlatformApplication.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepoInterface extends JpaRepository<Profile, Long> {

}
