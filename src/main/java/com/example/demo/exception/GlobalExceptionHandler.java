package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Capte les erreurs 404 (URL introuvable)
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handle404(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
            "status", "ERROR",
            "message", "Le chemin demandé n'existe pas. Vérifiez l'URL."
        ));
    }

    // Capte toutes les autres erreurs imprévues (Erreur 500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
            "status", "TECHNICAL_ERROR",
            "message", "Une erreur imprévue est survenue."
        ));
    }
}