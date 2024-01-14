package com.example.QuizPlatformApplication.controller;

import com.example.QuizPlatformApplication.controller.dto.AnswerDTO;
import com.example.QuizPlatformApplication.controller.dto.CorrectAnswersDTO;
import com.example.QuizPlatformApplication.controller.dto.DTOUtils;
import com.example.QuizPlatformApplication.controller.dto.QuestionInfoDTO;
import com.example.QuizPlatformApplication.model.*;
import com.example.QuizPlatformApplication.service.ServiceException;
import com.example.QuizPlatformApplication.service.interfaces.QuizServiceInterface;
import com.example.QuizPlatformApplication.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the taking quiz functionality.
 */
@Controller
@RequestMapping("/takequiz")
@CrossOrigin
public class TakeQuizController {
    /**
     * The quiz service.
     */
    @Autowired
    private QuizServiceInterface quizService;

    /**
     * The user service.
     */
    @Autowired
    private UserServiceInterface userService;

    /**
     * Returns the number of questions in a quiz.
     * @param quizId the id of the quiz
     * @return the number of questions in the quiz
     */
    @GetMapping("/quiz/{quizId}/question_count")
    public @ResponseBody ResponseEntity<?> getQuizQuestionCount(@PathVariable String quizId) {
        try {
            Long quizIdLong = Long.parseLong(quizId);
            Quiz quiz = quizService.getQuizById(quizIdLong);
            return ResponseEntity.ok(quiz.getQuizEntries().size());
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Returns the list of questions in a quiz.
     *
     * @param quizId the id of the quiz
     * @param username the username of the user
     * @return the list of questions in the quiz
     * 400 BAD REQUEST if the user is not found or the quiz is not started
     */
    @GetMapping("/quiz/{quizId}/questions")
    @CrossOrigin(origins="*")
    public @ResponseBody ResponseEntity<?> getQuizQuestions(@PathVariable String quizId, @RequestParam String username) {
        try {
            Long quizIdLong = Long.parseLong(quizId);
            Quiz quiz = quizService.getQuizById(quizIdLong);
            User user = userService.getUserByUsername(username);
            if(user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            }

            // this means that the quiz has to be started or has already ended to get the question
            if(quizService.getLastQuizProgress(quiz, user) == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quiz not started");
            }

            List<QuestionInfoDTO> questionList = new ArrayList<>();
            for (QuizEntry quizEntry : quiz.getQuizEntries()) {
                questionList.add(DTOUtils.getQuestionInfoDTO(quizEntry));
            }

            return ResponseEntity.ok(questionList);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Returns the question of a quiz with a specific id.
     *
     * @param quizId the id of the quiz
     * @param questionId the id of the question
     * @param username the username of the user
     * @return the question of the quiz with the specific id
     * 400 BAD REQUEST - if the user is not found or the quiz is not started
     */
    @GetMapping("/quiz/{quizId}/questions/{questionId}")
    public @ResponseBody ResponseEntity<?> getQuizQuestionOfId(@PathVariable String quizId, @PathVariable String questionId, @RequestParam String username) {
        try {
            Long quizIdLong = Long.parseLong(quizId);
            Long questionIdLong = Long.parseLong(questionId);

            Quiz quiz = quizService.getQuizById(quizIdLong);
            User user = userService.getUserByUsername(username);
            if(user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            }

            // this means that the quiz has to be started or has already ended to get the question
            if(quizService.getLastQuizProgress(quiz, user) == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quiz not started");
            }

            QuizEntry quizEntry = quizService.getQuizEntryById(questionIdLong);

            return ResponseEntity.ok(DTOUtils.getQuestionInfoDTO(quizEntry));
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Request to start the quiz for a specific user.
     *
     * @param quizId the id of the quiz
     * @param username the username of the user
     * @return 200 OK - if the quiz was started successfully
     * 400 BAD REQUEST - if the user is not found or the quiz is already started
     */
    @PostMapping("/quiz/{quizId}/start")
    public @ResponseBody ResponseEntity<?> startQuiz(@PathVariable String quizId, @RequestBody String username) {
        try {
            //System.out.println("ok!");
            Long quizIdLong = Long.parseLong(quizId);
            Quiz quiz = quizService.getQuizById(quizIdLong);
            User user = userService.getUserByUsername(username);
            System.out.println(user);

            if(user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            }

            if(quizService.isQuizStarted(quiz, user)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quiz already started");
            }

            quizService.startQuiz(quiz, user);

            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Request to end the quiz for a specific user.
     * @param quizId the id of the quiz
     * @param username the username of the user
     * @param userAnswers the list of answers of the user
     * @return 200 OK - if the quiz was ended successfully
     * 400 BAD REQUEST - if the user is not found or the quiz is not started
     */
    @PostMapping("/quiz/{quizId}/end")
    public @ResponseBody ResponseEntity<?> endQuiz(@PathVariable String quizId, @RequestParam String username, @RequestBody List<AnswerDTO> userAnswers) {
        try {
            Long quizIdLong = Long.parseLong(quizId);
            Quiz quiz = quizService.getQuizById(quizIdLong);

            User user = userService.getUserByUsername(username);
            if(user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            }

            if(!quizService.isQuizStarted(quiz, user)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quiz not started");
            }

            quizService.endQuiz(quiz, user, userAnswers);

            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Returns the information about the quiz.
     * @param quizId the id of the quiz
     * @param username the username of the user
     * @return the information about the quiz
     * 400 BAD REQUEST - if the user is not found or the quiz is not started
     */
    @GetMapping("/quiz/{quizId}/info")
    public @ResponseBody ResponseEntity<?> getQuizInformation(@PathVariable String quizId, @RequestBody String username) {
        try {
            Long quizIdLong = Long.parseLong(quizId);
            Quiz quiz = quizService.getQuizById(quizIdLong);

            User user = userService.getUserByUsername(username);
            if(user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            }

            if(!quizService.isQuizStarted(quiz, user)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quiz not started");
            }

            QuizProgress quizProgress = quizService.getLastQuizProgress(quiz, user);
            return ResponseEntity.ok(quizProgress);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Returns the correct answers of a quiz.
     *
     * @param quizId the id of the quiz
     * @param username the username of the user
     * @return the correct answers of a quiz
     * 400 BAD REQUEST - if the user is not found
     */
    @GetMapping("/quiz/{quizId}/answers")
    public @ResponseBody ResponseEntity<?> getQuizCorrectAnswers(@PathVariable String quizId, @RequestParam String username) {
        try {
            Long quizIdLong = Long.parseLong(quizId);
            Quiz quiz = quizService.getQuizById(quizIdLong);

            User user = userService.getUserByUsername(username);
            if(user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            }

            List<CorrectAnswersDTO> correctAnswers = new ArrayList<>();
            for (QuizEntry quizEntry : quiz.getQuizEntries()) {
                List<Pair<Long, String>> optionsAndExplanations = new ArrayList<>();
                List<Long> correctAnswersIds = new ArrayList<>();

                for (QuizOptions quizOption : quizEntry.getOptionAndExplanation()) {
                    String explanation = quizOption.getExplanation();

                    if (explanation != null) {
                        optionsAndExplanations.add(Pair.of(quizOption.getId(), explanation));
                    }

                    if (quizOption.isCorrectOption()) {
                        correctAnswersIds.add(quizOption.getId());
                    }
                }

                correctAnswers.add(new CorrectAnswersDTO(quizEntry.getId(), optionsAndExplanations, correctAnswersIds));
            }

            return ResponseEntity.ok(correctAnswers);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}