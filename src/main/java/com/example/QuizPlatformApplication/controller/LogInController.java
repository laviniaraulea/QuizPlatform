package com.example.QuizPlatformApplication.controller;

import com.example.QuizPlatformApplication.model.User;
import com.example.QuizPlatformApplication.security.jwttoken.JwtService;
import com.example.QuizPlatformApplication.service.interfaces.AuthenticationServiceInterface;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/mainPage")
    public String main(Model model, HttpSession session) {
        String token = (String) session.getAttribute("token");

        // Pass the token to view
        model.addAttribute("token", token);
        return "mainPage";
    }
}
