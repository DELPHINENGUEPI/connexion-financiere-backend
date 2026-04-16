package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String code;
    private LocalDateTime expiresAt;

    // getters & setters

    public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }


public String getCode() { return code; }
public void setCode(String code) { this.code = code; }

public LocalDateTime getExpiresAt() { return expiresAt; }
public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
}