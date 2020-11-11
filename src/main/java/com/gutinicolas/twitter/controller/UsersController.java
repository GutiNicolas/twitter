package com.gutinicolas.twitter.controller;

import com.gutinicolas.twitter.exceptions.TwitterException;
import com.gutinicolas.twitter.model.api.LoginRequest;
import com.gutinicolas.twitter.model.api.RegisterRequest;
import com.gutinicolas.twitter.model.api.TweetRequest;
import com.gutinicolas.twitter.model.entities.TweetEntity;
import com.gutinicolas.twitter.model.entities.UserEntity;
import com.gutinicolas.twitter.service.AuthService;
import com.gutinicolas.twitter.service.TweetsService;
import com.gutinicolas.twitter.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@ControllerAdvice
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public Map<String, Object> register(@Valid @RequestBody RegisterRequest registerRequest, HttpServletRequest request) throws TwitterException {
        UserEntity user = usersService.register(registerRequest);
        return Map.of("status", "ok", "message", "@"+user.getUsername()+" registered.");
    }

    @PostMapping("/login")
    public Map<String, Object> addEmotion(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) throws TwitterException {
        return authService.login(loginRequest);
    }
}
