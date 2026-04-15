package com.example.demo.service;

import com.example.demo.model.Otp;
import com.example.demo.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    // 1. Générer OTP
    public String generateOtp(String email) {

        String code = String.valueOf((int)(Math.random() * 900000) + 100000);

        Otp otp = new Otp();
        otp.setEmail(email);
        otp.setCode(code);
        otp.setExpiresAt(LocalDateTime.now().plusMinutes(5));

        otpRepository.save(otp);

        return code;
    }

    // 2. Vérifier OTP
    public boolean verifyOtp(String email, String code) {

        Optional<Otp> otpOpt = otpRepository.findByEmail(email);

        if (otpOpt.isEmpty()) return false;

        Otp otp = otpOpt.get();

        return otp.getCode().equals(code)
                && otp.getExpiresAt().isAfter(LocalDateTime.now());
    }
}