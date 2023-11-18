package com.example.QuizPlatformApplication.controller;

import com.example.QuizPlatformApplication.controller.dto.EntryQuizDTO;
import com.example.QuizPlatformApplication.controller.dto.QuizDTO;
import com.example.QuizPlatformApplication.model.Quiz;
import com.example.QuizPlatformApplication.model.QuizEntry;
import com.example.QuizPlatformApplication.service.ServiceException;
import com.example.QuizPlatformApplication.service.implementation.QuizServiceImpl;
import com.example.QuizPlatformApplication.service.interfaces.QuizServiceInterface;
import com.example.QuizPlatformApplication.service.interfaces.UserServiceInterface;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
// aici ar trebui sa fie /quiz dupa ce e rezolvat JWT
@RequestMapping("/login")
@CrossOrigin
public class QuizController {
    @Autowired
    private QuizServiceInterface quizService;

    @Autowired
    private UserServiceInterface userService;

    @GetMapping("/get/quizzes")
    public @ResponseBody ResponseEntity<?> getQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @PostMapping("/create/quiz")
    public @ResponseBody ResponseEntity<?> createQuiz(@RequestBody QuizDTO quizDTO) {
        System.out.println("intra");
        try {
            Quiz quiz = new Quiz(userService.getUserByUsername(quizDTO.getUsername_owner()), quizDTO.getCategory(), quizDTO.getDifficulty(),quizDTO.getTimeLimit(),quizDTO.getDescription(),quizDTO.isCanSeeResult(),quizDTO.getPassingScore(),quizDTO.isMinimumScoreRequired());
            quizService.createQuiz(quiz);
            return ResponseEntity.ok(quiz);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/create/question")
    public @ResponseBody ResponseEntity<?> createQuestion(@RequestBody QuizEntry quizEntry) {
        try {
            quizService.createQuestion(quizEntry);
            return ResponseEntity.ok(quizEntry);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * @param entryQuizDTO: contine id-ul intrebarii, si id-ul quizului la care va fi asignata
     * @return ok- daca intrebarea a fost adaugata cu succes
     * */
    @PutMapping("/add/question")
    public @ResponseBody ResponseEntity<?> addQuestion(@RequestBody EntryQuizDTO entryQuizDTO) {
        try {
            Quiz quiz = quizService.getQuizById(entryQuizDTO.getQuizId());
            QuizEntry quizEntry = quizService.getQuizEntryById(entryQuizDTO.getQuizEntryId());
            quizService.addQuestionToQuiz(quiz, quizEntry);
            return ResponseEntity.ok(entryQuizDTO);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * @param entryQuizDTO: contine id-ul intrebarii, si id-ul quizului din care va fi eliminata
     * @return ok- daca intrebarea a fost adaugata cu succes
     * */
    @PutMapping("/delete/question")
    public @ResponseBody ResponseEntity<?> deleteQuestion(@RequestBody EntryQuizDTO entryQuizDTO) {
        try {
            Quiz quiz = quizService.getQuizById(entryQuizDTO.getQuizId());
            QuizEntry quizEntry = quizService.getQuizEntryById(entryQuizDTO.getQuizEntryId());
            quizService.deleteQuestionFromQuiz(quiz,quizEntry);
            return ResponseEntity.ok(quizEntry);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
