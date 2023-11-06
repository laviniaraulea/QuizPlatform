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
    @Id
    // Generated using the sequence strategy
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;
    public User() {
    }
}
