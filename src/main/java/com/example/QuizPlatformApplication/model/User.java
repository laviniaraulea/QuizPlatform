package com.example.QuizPlatformApplication.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class User implements Serializable {
    /**
     * Unique identifier for the user.
     */
    @Id
    // Generated using the sequence strategy
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User username.
     */
    @Column
    private String username;

    /**
     * User password.
     */
    @Column
    private String password;

    /**
     * User Date of birth.
     */
    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;

    public User() {
    }
}
