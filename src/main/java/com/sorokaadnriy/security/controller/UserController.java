package com.sorokaadnriy.security.controller;

import com.sorokaadnriy.security.config.service.UserService;
import com.sorokaadnriy.security.entity.User;
import com.sorokaadnriy.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository repository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        String isLogin = userService.login(user);
        return isLogin;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.register(user);
    }


}
