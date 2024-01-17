package com.example.QuizPlatformApplication.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * Entity class representing a profile in the Quiz Platform Application.
 */
@Entity
@Data
@Table(name = "profiles")
public class Profile implements Serializable {
    /**
     * Unique identifier for the profile.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User of the profile.
     */
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    /**
     * Number of quizzes solved by user.
     */
    @Column
    private int noSolved;

    /**
     * Number of quizzes created by user.
     */
    @Column
    private int noCreated;

    public Profile() {
    }

}
