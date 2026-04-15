package com.example.demo.controller;

import com.example.demo.model.Prospect;
import com.example.demo.repository.ProspectRepository;
import com.example.demo.service.OtpService;
import com.example.demo.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ProspectController {

    @Autowired
    private ProspectRepository prospectRepository;

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    // 1. Créer prospect + envoyer OTP
    @PostMapping("/prospect")
    public String createProspect(@RequestBody Prospect prospect) {

        // sauvegarde utilisateur
        prospectRepository.save(prospect);

        // générer OTP
        String otp = otpService.generateOtp(prospect.getEmail());

        // envoyer OTP (console pour l'instant)
        emailService.sendOtp(prospect.getEmail(), otp);

        return "OTP_SENT";
    }

    // 2. Vérifier OTP
    @PostMapping("/verify")
    public String verifyOtp(@RequestBody Map<String, String> body) {

        String email = body.get("email");
        String code = body.get("code");

        boolean valid = otpService.verifyOtp(email, code);

        if (!valid) {
            return "INVALID_OTP";
        }

        Prospect p = prospectRepository.findByEmail(email)
                .orElseThrow();

        p.setVerified(true);
        prospectRepository.save(p);

        return "VERIFIED";
    }
}
