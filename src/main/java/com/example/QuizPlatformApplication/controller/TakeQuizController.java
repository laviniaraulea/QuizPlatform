package com.example.QuizPlatformApplication.controller;

import com.example.QuizPlatformApplication.controller.dto.*;
import com.example.QuizPlatformApplication.model.*;
import com.example.QuizPlatformApplication.security.jwttoken.JwtService;
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

@Controller
@RequestMapping("/takequiz")
@CrossOrigin
public class TakeQuizController {
    @Autowired
    private QuizServiceInterface quizService;

    @Autowired
    private UserServiceInterface userService;

    @GetMapping("/quiz/{quizId}/question_count")
    public @ResponseBody ResponseEntity<?> getQuizQuestionCount(@PathVariable String quizId) {
        try {
            Long quizIdLong = Long.parseLong(quizId);
            Quiz quiz = quizService.getQuizById(quizIdLong);
            return ResponseEntity.ok(quiz.getQuizEntries().size());
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Quiz Id format");
        }
    }

    @GetMapping("/quiz/{quizId}/questions")
    public @ResponseBody ResponseEntity<?> getQuizQuestions(@PathVariable String quizId, @RequestBody String username) {
        // TODO: in the future we will verify the identity of the user by using JWT
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

    @GetMapping("/quiz/{quizId}/questions/{questionId}")
    public @ResponseBody ResponseEntity<?> getQuizQuestionOfId(@PathVariable String quizId, @PathVariable String questionId, @RequestBody String username) {
        // TODO: in the future we will verify the identity of the user by using JWT
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

    @PostMapping("/quiz/{quizId}/start")
    public @ResponseBody ResponseEntity<?> startQuiz(@PathVariable String quizId, @RequestBody String username) {
        // TODO: in the future we will verify the identity of the user by using JWT
        try {
            Long quizIdLong = Long.parseLong(quizId);
            Quiz quiz = quizService.getQuizById(quizIdLong);
            User user = userService.getUserByUsername(username);

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

    @PostMapping("/quiz/{quizId}/end")
    public @ResponseBody ResponseEntity<?> endQuiz(@PathVariable String quizId, @RequestBody EndQuizRequest request) {
        // TODO: in the future we will verify the identity of the user by using JWT
        try {
            String username = request.getUsername();
            List<AnswerDTO> userAnswers = request.getUserAnswers();

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

    @GetMapping("/quiz/{quizId}/info")
    public @ResponseBody ResponseEntity<?> getQuizInformation(@PathVariable String quizId, @RequestBody String username) {
        // TODO: in the future we will verify the identity of the user by using JWT
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
            QuizProgressDTO quizProgressDTO = DTOUtils.getFromDTO(quizProgress);
            return ResponseEntity.ok(quizProgressDTO);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/quiz/{quizId}/answers")
    public @ResponseBody ResponseEntity<?> getQuizCorrectAnswers(@PathVariable String quizId, @RequestBody String username) {
        // TODO: in the future we will verify the identity of the user by using JWT
        try {
            Long quizIdLong = Long.parseLong(quizId);
            Quiz quiz = quizService.getQuizById(quizIdLong);

            User user = userService.getUserByUsername(username);
            if(user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            }

            QuizProgress quizProgress = quizService.getLastQuizProgress(quiz, user);
            if(quizProgress == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quiz not started");
            }
            if(!quizProgress.getHasEnded()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quiz in progress");
            }

            List<CorrectAnswersDTO> correctAnswers = new ArrayList<>();
            for (QuizEntry quizEntry : quiz.getQuizEntries()) {
                List<Pair<Long, String>> optionsAndExplanations = new ArrayList<>();
                List<Long> correctAnswersIds = new ArrayList<>();

                for(QuizOptions quizOption : quizEntry.getOptionAndExplanation()) {
                    optionsAndExplanations.add(Pair.of(quizOption.getId(), quizOption.getExplanation()));
                    if(quizOption.isCorrectOption()) {
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
