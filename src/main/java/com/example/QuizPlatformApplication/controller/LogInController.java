package com.example.QuizPlatformApplication.controller;

import com.example.QuizPlatformApplication.controller.dto.UserDTO;
import com.example.QuizPlatformApplication.model.User;
import com.example.QuizPlatformApplication.security.jwttoken.JwtService;
import com.example.QuizPlatformApplication.service.ServiceException;
import com.example.QuizPlatformApplication.service.interfaces.AuthenticationServiceInterface;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/login")
@CrossOrigin
public class LogInController {

    @Autowired
    private AuthenticationServiceInterface authenticationService;

    @Autowired
    private JwtService jwtService;

    /**
     * @param user: userul ce vrea sa fie logat
     * @return unauthorized - daca nu exista in db sau daca nu se matchuiesc parolele
     * @return jwtToken sub forma de string daca se matchuiesc
     * */
    @PostMapping("/auth")
    public @ResponseBody ResponseEntity<?> login(@RequestBody User user, HttpServletRequest request){
        if(authenticationService.logIn(user)){
            String token = jwtService.generateToken(user);
            HttpSession session = request.getSession();
            session.setAttribute("token", token);
            return ResponseEntity.ok(token);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * Registers a user
     * @param user UserDTO object containing the user's data
     * @return 200 OK - if the user was registered successfully
     * 400 BAD REQUEST - if the data is invalid
     */
    @PostMapping("/register")
    public @ResponseBody ResponseEntity<?> registerUser(@RequestBody UserDTO user) {
        String dob = user.getDateOfBirth();
        LocalDate dateOfBirth;
        try {
            dateOfBirth = LocalDate.parse(dob);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date format");
        }

        try {
            authenticationService.signUp(user.getUsername(), user.getPassword(), dateOfBirth);
            return ResponseEntity.ok().build();
        }
        catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not register user.");
        }
    }

    @GetMapping("/mainPage")
    public String main(Model model, HttpSession session) {
        String token = (String) session.getAttribute("token");

        // Pass the token to view
        model.addAttribute("token", token);
        return "mainPage";
    }

    @GetMapping("/userStats")
    public String stats(Model model, HttpSession session) {
        String token = (String) session.getAttribute("token");

        // Pass the token to view
        model.addAttribute("token", token);
        return "userStats";
    }

    @GetMapping("/createQuiz")
    public String create(Model model, HttpSession session){
        String token = (String) session.getAttribute("token");
        System.out.println(token);
        model.addAttribute("token", token);
        return "createQuiz";
    }

    @GetMapping("/takeQuiz")
    public String take(Model model, HttpSession session){
        String token = (String) session.getAttribute("token");
        System.out.println(token);
        model.addAttribute("token", token);
        return "takeQuiz";
    }
}
