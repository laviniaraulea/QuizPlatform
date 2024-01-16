package com.example.QuizPlatformApplication.model.validator;

import com.example.QuizPlatformApplication.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *  Component class responsible for validating Quiz and QuizEntry objects.
 */
@Component
public class Validator {

    /**
     * Validates a new quiz.
     * @param quiz The quiz to be validated.
     * @throws MyException If validation fails, an exception with error messages is thrown.
     */
    public void validateNewQuiz(Quiz quiz) throws MyException {
        List<String> errorMessages = new ArrayList<>();

        if (quiz.getTitle() == null || quiz.getTitle().equals("")) {
            errorMessages.add("The title can not be empty!");
        }
        if (quiz.getDescription() == null || quiz.getDescription().equals("")) {
            errorMessages.add("The description can not be empty!");
        }

        if (quiz.getTimeLimit() <= 0) {
            errorMessages.add("The time limit must be bigger than 0!");
        }
        if (quiz.getPassingScore() <= 0) {
            errorMessages.add("The passing score must be bigger than 0!");
        }

        if (!errorMessages.isEmpty()) {
            throw new MyException(String.join("\n", errorMessages));
        }
    }

    /**
     * Validates a new quiz question (QuizEntry).
     * @param quizEntry The quiz question to be validated.
     * @throws MyException If validation fails, an exception with error messages is thrown.
     */
    public void validateNewQuestion(QuizEntry quizEntry) throws MyException {
        List<String> errorMessages = new ArrayList<>();

        if (quizEntry.getQuestion() == null || quizEntry.getQuestion().equals("")) {
            errorMessages.add("The question can not be empty!");
        }
        if (quizEntry.getHint() == null || quizEntry.getHint().equals("")) {
            errorMessages.add("The hint can not be empty!");
        }

        // validate options
        int nrCorrectAnswers = 0;
        if (quizEntry.getOptionAndExplanation().size() == 0) {
            errorMessages.add("You can not add a question without options!");
        }
        for (QuizOptions quizOption : quizEntry.getOptionAndExplanation()) {
            System.out.println(quizOption.isCorrectOption());
            System.out.println(quizOption.getIsMultipleChoiceStr());
            if (quizOption.isCorrectOption()) {
                nrCorrectAnswers++;
            }
            if (quizOption.getOption() == null || quizOption.getOption().equals("")) {
                errorMessages.add("The option can not be empty!");
            }
            if (quizOption.getExplanation() == null || quizOption.getExplanation().equals("")) {
                errorMessages.add("The explanation can not be empty!");
            }
        }

        if (nrCorrectAnswers == 0 && !quizEntry.isMultipleChoice()) {
            errorMessages.add("You must select at least one correct answer!" + nrCorrectAnswers + "   " + quizEntry.isMultipleChoice() + "  " + quizEntry.getHint());
        }

        if (nrCorrectAnswers < 2 && quizEntry.isMultipleChoice()) {
            errorMessages.add("You must select at least two correct answers!");
        }

        if (!errorMessages.isEmpty()) {
            throw new MyException(String.join("\n", errorMessages));
        }
    }

    /**
     * Validates adding a question to a quiz.
     * @param quiz The quiz to which the question is being added.
     * @param quizEntry The quiz question to be added.
     * @throws MyException If validation fails, an exception with error messages is thrown.
     */
    public void validateAddQuestionToQuiz(Quiz quiz, QuizEntry quizEntry) throws MyException {
        List<String> errorMessages = new ArrayList<>();

        if (quizEntry.getQuiz() != null && quizEntry.getQuiz().getId().equals(quiz.getId())) {
            errorMessages.add("The question is already added to the quiz!");
        }

        if (!errorMessages.isEmpty()) {
            throw new MyException(String.join("\n", errorMessages));
        }
    }
}