package com.example.QuizPlatformApplication.controller;

import com.example.QuizPlatformApplication.controller.dto.EntryQuizDTO;
import com.example.QuizPlatformApplication.controller.dto.QuizDTO;
import com.example.QuizPlatformApplication.model.Quiz;
import com.example.QuizPlatformApplication.model.QuizEntry;
import com.example.QuizPlatformApplication.model.validator.MyException;
import com.example.QuizPlatformApplication.service.ServiceException;
import com.example.QuizPlatformApplication.service.implementation.QuizServiceImpl;
import com.example.QuizPlatformApplication.service.interfaces.QuizServiceInterface;
import com.example.QuizPlatformApplication.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for managing quizzes and quiz entries.
 */
@Controller
@RequestMapping("/quiz")
@CrossOrigin
public class QuizController {
    @Autowired
    private QuizServiceInterface quizService;

    @Autowired
    private UserServiceInterface userService;

    /**
     * Retrieves all quizzes.
     * @return ResponseEntity with a list of all quizzes or an empty list if none are found.
     */
    @GetMapping("/get/quizzes")
    public @ResponseBody ResponseEntity<?> getQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    /**
     * Retrieves quizzes based on the specified category.
     * @param category  The category for which quizzes are to be retrieved.
     * @return ResponseEntity with a list of quizzes matching the specified category.
     */
    @GetMapping("/get/quizzesByCategory")
    public @ResponseBody ResponseEntity<?> getQuizzesByCategory(@RequestParam String category) {
        List<Quiz> quizzes = quizService.getQuizzesByCategory(category);
        return ResponseEntity.ok(quizzes);
    }

    /**
     * Retrieves quizzes based on the specified difficulty.
     * @param difficulty The difficulty for which quizzes are to be retrieved.
     * @return ResponseEntity with a list of quizzes matching the specified difficulty.
     */
    @GetMapping("/get/quizzesByDifficulty")
    public @ResponseBody ResponseEntity<?> quizzesByDifficulty(@RequestParam String difficulty) {
        List<Quiz> quizzes = quizService.getQuizzesByDifficulty(difficulty);
        return ResponseEntity.ok(quizzes);
    }

    /**
     * Creates a new quiz.
     * @param quizDTO Data Transfer Object containing information about the quiz to be created.
     * @return ResponseEntity with the created quiz or an error message if creation fails.
     */
    @PostMapping("/create/quiz")
    public @ResponseBody ResponseEntity<?> createQuiz(@RequestBody QuizDTO quizDTO) throws MyException {
        try {
            Quiz quiz = new Quiz(userService.getUserByUsername(quizDTO.getUsername_owner()), quizDTO.getCategory(), quizDTO.getDifficulty(),quizDTO.getTimeLimit(),quizDTO.getDescription(),quizDTO.isCanSeeResult(),quizDTO.getPassingScore(),quizDTO.isMinimumScoreRequired(), quizDTO.getTitle());
            quizService.createQuiz(quiz);
            return ResponseEntity.ok(quiz);
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + e.getMessage() + "\"}");
        }
    }

    /**
     * Create a question.
     * @param quizEntry The quiz entry representing the new question.
     * @return ResponseEntity with the created question or an error message if creation fails.
     */
    @PostMapping("/create/question")
    public @ResponseBody ResponseEntity<?> createQuestion(@RequestBody QuizEntry quizEntry) {
        try {
            quizService.createQuestion(quizEntry);
            return ResponseEntity.ok(quizEntry);
        }  catch (MyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Adds an existing question to a quiz.
     * @param entryQuizDTO Data Transfer Object containing IDs of the question and the quiz.
     * @return ResponseEntity indicating success or an error message if the operation fails.
     * */
    @PutMapping("/add/question")
    public @ResponseBody ResponseEntity<?> addQuestion(@RequestBody EntryQuizDTO entryQuizDTO) {
        try {
            Quiz quiz = quizService.getQuizById(entryQuizDTO.getQuizId());
            QuizEntry quizEntry = quizService.getQuizEntryById(entryQuizDTO.getQuizEntryId());
            quizService.addQuestionToQuiz(quiz, quizEntry);
            return ResponseEntity.ok(entryQuizDTO);
        } catch (MyException | ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Deletes a question from a quiz.
     * @param entryQuizDTO entryQuizDTO Data Transfer Object containing IDs of the question and the quiz.
     * @return ResponseEntity indicating success or an error message if the operation fails.
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
