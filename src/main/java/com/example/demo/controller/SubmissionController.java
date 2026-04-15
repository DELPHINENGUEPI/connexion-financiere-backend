package com.example.demo.controller;

import com.example.demo.entity.Submission;
import com.example.demo.repository.SubmissionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
@CrossOrigin("*")
public class SubmissionController {

    private final SubmissionRepository repo;

    public SubmissionController(SubmissionRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Submission save(@RequestBody Submission s) {
        return repo.save(s);
    }

    @GetMapping
    public List<Submission> all() {
        return repo.findAll();
    }
}