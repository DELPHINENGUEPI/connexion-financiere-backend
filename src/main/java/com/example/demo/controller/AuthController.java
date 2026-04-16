package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder; // Import manquant résolu

import jakarta.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") 
public class AuthController {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        if (repo.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of(
                "status", "ERROR",
                "message", "Cet email est déjà utilisé"
            ));
        }
        
        // Hachage du mot de passe avant sauvegarde
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
        
        return ResponseEntity.ok(Map.of(
            "status", "SUCCESS",
            "message", "Inscription sécurisée !"
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> existingUser = repo.findByEmail(user.getEmail());

        if (existingUser.isPresent() && 
            passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            
            return ResponseEntity.ok(Map.of(
                "status", "SUCCESS",
                "message", "Connexion réussie",
                "userName", existingUser.get().getName()
            ));
        }

        return ResponseEntity.status(401).body(Map.of(
            "status", "FAILED", 
            "message", "Email ou mot de passe incorrect"
        ));
    }
}