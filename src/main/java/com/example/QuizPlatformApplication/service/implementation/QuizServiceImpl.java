package com.example.QuizPlatformApplication.service.implementation;

import com.example.QuizPlatformApplication.model.Quiz;
import com.example.QuizPlatformApplication.model.QuizEntry;
import com.example.QuizPlatformApplication.repository.QuizEntryRepoInterface;
import com.example.QuizPlatformApplication.repository.QuizRepoInterface;
import com.example.QuizPlatformApplication.service.ServiceException;
import com.example.QuizPlatformApplication.service.interfaces.QuizServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizServiceInterface {
    @Autowired
    private QuizRepoInterface quizRepoInterface;

    @Autowired
    private QuizEntryRepoInterface quizEntryRepoInterface;

    @Override
    public void createQuiz(Quiz quiz) throws ServiceException {
        // Validare pentru asigurarea ca quiz-ul nu este null si are toate informatiile necesare
        if (quiz == null || quiz.getOwner() == null || quiz.getCategory() == null || quiz.getDifficulty() == null) {
            throw new ServiceException("Invalid quiz. Make sure all the required information is provided.");
        }
        quizRepoInterface.save(quiz);
    }

    @Override
    public void createQuestion(QuizEntry quizEntry) throws ServiceException {
        if (quizEntry == null || quizEntry.getQuestion() == null || quizEntry.getOptionAndExplanation() == null || quizEntry.getHint() == null) {
            throw new ServiceException("Invalid question. Make sure all the required information is provided.");
        }
        quizEntryRepoInterface.save(quizEntry);
    }

    @Override
    public void addQuestionToQuiz(Quiz quiz, QuizEntry quizEntry) throws ServiceException {
        // Validation to ensure quiz and quizEntry are not null
        if (quiz == null || quizEntry == null) {
            throw new ServiceException("Invalid quiz or question.");
        }

        // Validation to ensure the question does not already exist in the quiz
        if (quiz.getQuizEntries().contains(quizEntry)) {
            throw new ServiceException("The question already exists in the quiz.");
        }
        quiz.getQuizEntries().add(quizEntry);
        quizRepoInterface.save(quiz);
    }

    @Override
    public void deleteQuestionFromQuiz(Quiz quiz, QuizEntry quizEntry) throws ServiceException {
        // Validation to ensure quiz and quizEntry are not null
        if (quiz == null || quizEntry == null) {
            throw new ServiceException("Invalid quiz or question.");
        }

        // Validation to ensure the question exists in the quiz before attempting to remove it
        if (!quiz.getQuizEntries().contains(quizEntry)) {
            throw new ServiceException("The question does not exist in the quiz.");
        }
        quiz.getQuizEntries().remove(quizEntry);
        quizRepoInterface.save(quiz);
    }

    @Override
    public Quiz getQuizById(Long id) throws ServiceException {
        // Validare pentru identificatorul null sau negativ
        if (id == null || id <= 0) {
            throw new ServiceException("Invalid quiz ID provided.");
        }

        // Cautarea quiz-ului in baza de date
        Optional<Quiz> quizOptional = quizRepoInterface.findById(id);
        if (quizOptional.isPresent()) {
            return quizOptional.get();
        } else {
            throw new ServiceException("Quiz not found for ID: " + id);
        }
    }

    @Override
    public QuizEntry getQuizEntryById(Long id) throws ServiceException {
        // Validare pentru identificatorul null sau negativ
        if (id == null || id <= 0) {
            throw new ServiceException("Invalid quiz entry ID provided.");
        }

        // Cautarea intrarii de quiz in baza de date
        Optional<QuizEntry> quizEntryOptional = quizEntryRepoInterface.findById(id);
        if (quizEntryOptional.isPresent()) {
            return quizEntryOptional.get();
        } else {
            throw new ServiceException("Quiz entry not found for ID: " + id);
        }
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepoInterface.findAll();
    }
}
