package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

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

    User existingUser = repo.findAll().stream()
        .filter(u -> u.getEmail().equals(user.getEmail())
                  && u.getPassword().equals(user.getPassword()))
        .findFirst()
        .orElse(null);

    if (existingUser != null) {
        return "LOGIN SUCCESS";
    } else {
        return "LOGIN FAILED";
    }
}
}

