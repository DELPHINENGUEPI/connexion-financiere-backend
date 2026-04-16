package com.example.demo.controller;

import com.example.demo.entity.Submission;
import com.example.demo.repository.SubmissionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/submissions")
@CrossOrigin("*")
public class SubmissionController {

    private final SubmissionRepository repo;

    public SubmissionController(SubmissionRepository repo) {
        this.repo = repo;
    }

    /**
     * POST → Sauvegarder une soumission avec sécurité anti-null
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody Submission s) {
        try {
            // Sécurité : on vérifie que l'objet reçu n'est pas vide
            if (s == null) {
                return ResponseEntity.badRequest().body(Map.of("status", "ERROR", "message", "Données invalides"));
            }

            // Sauvegarde et vérification immédiate du résultat pour l'IDE (Null Safety)
            Submission saved = repo.save(s);
            
            // Utilisation de Objects.requireNonNull pour garantir à Java que 'saved' n'est pas null
            Objects.requireNonNull(saved, "L'enregistrement a échoué en base de données");

            return ResponseEntity.ok(Map.of(
                "status", "SUCCESS",
                "message", "Données enregistrées avec succès",
                "id", saved.getId()
            ));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                "status", "ERROR",
                "message", "Erreur technique : " + e.getMessage()
            ));
        }
    }

    /**
     * GET → Récupérer tout
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Submission>> all() {
        List<Submission> submissions = repo.findAll();
        return ResponseEntity.ok(submissions);
    }
}