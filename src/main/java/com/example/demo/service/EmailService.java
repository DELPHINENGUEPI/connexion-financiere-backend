package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    // VERSION TEST (console)
    // on active vrai email plus tard

    public void sendOtp(String email, String code) {

        System.out.println("===== OTP EMAIL =====");
        System.out.println("To: " + email);
        System.out.println("Code: " + code);
        System.out.println("=====================");
    }
}