package com.example.QuizPlatformApplication.controller;

import com.example.QuizPlatformApplication.model.User;
import com.example.QuizPlatformApplication.security.jwttoken.JwtService;
import com.example.QuizPlatformApplication.service.interfaces.AuthenticationServiceInterface;
import com.example.QuizPlatformApplication.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

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
    public @ResponseBody ResponseEntity<?> login(@RequestBody User user){
        if(authenticationService.logIn(user)){
            return ResponseEntity.ok(jwtService.generateToken(user));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping
    public String login() {
        return "login";
    }
}
