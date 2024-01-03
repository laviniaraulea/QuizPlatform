package com.example.QuizPlatformApplication.model.validator;

import com.example.QuizPlatformApplication.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class Validator {

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

//        if(quiz.getQuizEntries().size() == 0){
//            errorMessages.add("You can not add a quiz without at least one question!");
//        }


        if (!errorMessages.isEmpty()) {
            throw new MyException(String.join("\n", errorMessages));
        }
    }

    public void validateNewQuestion(QuizEntry quizEntry) throws MyException {
        List<String> errorMessages = new ArrayList<>();

        if (quizEntry.getQuestion() == null || quizEntry.getQuestion().equals("")) {
            errorMessages.add("The question can not be empty!");
        }
        if (quizEntry.getHint() == null || quizEntry.getHint().equals("")) {
            errorMessages.add("The hint can not be empty!");
        }


//        try {
//            QuizCategory quizCategory = quiz.getCategory();
//        } catch (Exception e) {
//            throw new MyException("The category is not valid!", e);
//        }
//        try {
//            QuizDifficulty quizDifficulty = quiz.getDifficulty();
//        } catch (Exception e) {
//            throw new MyException("The difficulty is not valid!", e);
//        }


        // validate options
        int nrCorrectAnswers = 0;
        if (quizEntry.getOptionAndExplanation().size() == 0) {
            errorMessages.add("You can not add a question without options!");
        }
        for (QuizOptions quizOption : quizEntry.getOptionAndExplanation()) {
            System.out.println(quizOption.isCorrectOption());
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