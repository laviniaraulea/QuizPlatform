package com.example.QuizPlatformApplication.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "userAnswers")
public class UserAnswers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quizId")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column
    private String question;

    @Column
    private String answer;

    public UserAnswers() {
    }
}
