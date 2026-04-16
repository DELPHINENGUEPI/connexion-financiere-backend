package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List; // Import nécessaire pour la liste

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // 1. Envoi individuel (OTP)
    public void sendOtp(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("meekdelphine603@gmail.com"); 
        message.setTo(to);
        message.setSubject("Votre Code de Vérification - Connexion Financière");
        message.setText("Bonjour,\n\n" +
                "Merci de rejoindre Connexion Financière. " +
                "Voici votre code de vérification unique : " + otp + "\n\n" +
                "Ce code est nécessaire pour sécuriser votre accès et valider votre dossier.\n" +
                "Il est le seul et ça restera ainsi.\n\n" +
                "Cordialement,\n L'équipe de Delphine Nguepi");

        try {
            mailSender.send(message);
            System.out.println("Email envoyé avec succès à : " + to);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }
    }

    // 2. Envoi groupé (Mass Mailing pour tes 1 000 leads)
    public void sendMassEmail(List<String> recipients, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("meekdelphine603@gmail.com");
        message.setSubject(subject);
        message.setText(content + "\n\nIl est le seul et ça restera ainsi.");

        for (String email : recipients) {
            message.setTo(email);
            try {
                mailSender.send(message);
                System.out.println("Email groupé envoyé à : " + email);
            } catch (Exception e) {
                System.err.println("Échec de l'envoi pour : " + email + " | Erreur : " + e.getMessage());
            }
        }
    }
}