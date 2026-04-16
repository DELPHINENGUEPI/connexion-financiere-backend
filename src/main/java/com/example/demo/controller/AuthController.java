package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserRepository repo;

    public AuthController(UserRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return repo.save(user);
    }

  @PostMapping("/login")
public String login(@RequestBody User user) {

    User existingUser = repo.findByEmail(user.getEmail()).orElse(null);

    if (existingUser != null &&
        existingUser.getPassword().equals(user.getPassword())) {

        return "LOGIN SUCCESS";
    }

    return "LOGIN FAILED";
}
}