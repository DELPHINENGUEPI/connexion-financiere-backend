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
@CrossOrigin(origins = "*") 
public class ProspectController {

    @Autowired
    private ProspectRepository prospectRepository;

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    // 1. Créer prospect + envoyer OTP
    @PostMapping(value = "/prospect", consumes = "application/json", produces = "application/json")
    public Map<String, String> createProspect(@RequestBody Prospect prospect) {

        if (prospect.getEmail() == null || prospect.getEmail().isEmpty()) {
            return Map.of("status", "EMAIL_REQUIRED");
        }

        // Sauvegarde du prospect
        prospectRepository.save(prospect);

        // Génération et envoi de l'OTP
        String otp = otpService.generateOtp(prospect.getEmail());
        emailService.sendOtp(prospect.getEmail(), otp);

        return Map.of("status", "OTP_SENT");
    }

    // 2. Vérifier OTP
    @PostMapping(value = "/verify", consumes = "application/json", produces = "application/json")
    public Map<String, String> verifyOtp(@RequestBody Map<String, String> body) {

        String email = body.get("email");
        String code = body.get("code");

        if (email == null || code == null) {
            return Map.of("status", "MISSING_DATA");
        }

        boolean valid = otpService.verifyOtp(email, code);

        if (!valid) {
            return Map.of("status", "INVALID_OTP");
        }

        return prospectRepository.findByEmail(email)
                .map(p -> {
                    p.setVerified(true);
                    prospectRepository.save(p);
                    return Map.of("status", "VERIFIED");
                })
                .orElse(Map.of("status", "USER_NOT_FOUND"));
    }
}