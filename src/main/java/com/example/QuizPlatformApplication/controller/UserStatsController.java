package com.example.QuizPlatformApplication.controller;

import com.example.QuizPlatformApplication.controller.dto.FailedAndPassDTO;
import com.example.QuizPlatformApplication.controller.dto.GeneralStatsDTO;
import com.example.QuizPlatformApplication.controller.dto.UsernameCategoryDTO;
import com.example.QuizPlatformApplication.model.User;
import com.example.QuizPlatformApplication.service.interfaces.QuizServiceInterface;
import com.example.QuizPlatformApplication.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for the taking user stats functionality.
 */
@Controller
@RequestMapping("/userStats")
@CrossOrigin
public class UserStatsController {
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
     * Returns the general stats for a user
     * @param username the username of a user
     * @return the total quizzes created, total quizzes done, average score of the user
     */
    @GetMapping("/getGeneralStats")
    public @ResponseBody ResponseEntity<?> getGeneralStats(@RequestParam String username) {
        try {
            User user = userService.getUserByUsername(username);
            if(user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            }
            GeneralStatsDTO generalStatsDTO = quizService.getGeneralStats(user);
            return ResponseEntity.ok(generalStatsDTO);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username");
        }
    }


    /**
     * Returns the stats per category for a user
     * @param data contain the username and category
     * @return the stats for a specific catefory
     */
    @PostMapping("/getStatsPerCategory")
    public @ResponseBody ResponseEntity<?> getStatsPerCategory(@RequestBody UsernameCategoryDTO data) {
        try {
            String username = data.getUsername();
            String category = data.getCategory();

            User user = userService.getUserByUsername(username);
            if(user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            }

            GeneralStatsDTO generalStatsDTO = quizService.getStatsPerCategory(user, category);
            return ResponseEntity.ok(generalStatsDTO);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username");
        }
    }

    /**
     * Returns the number of Failed Quizzes and the number of Paassed Quizzes for a user
     * @param username the username of a user
     * @return  number of Failed Quizzes and number of Paassed Quizzes
     */
    @GetMapping("/getFailedAndPassStats")
    public @ResponseBody ResponseEntity<?> getFailedAndPass(@RequestParam String username) {
        try {
            User user = userService.getUserByUsername(username);
            if(user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            }

            FailedAndPassDTO failedAndPass = quizService.getFailedAndPass(user);
            return ResponseEntity.ok(failedAndPass);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username");
        }
    }
}


