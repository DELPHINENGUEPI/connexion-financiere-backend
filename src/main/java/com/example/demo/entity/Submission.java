package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // Constructeur vide (Obligatoire pour JPA)
    public Submission() {
        
    }

    // AJOUTE BIEN CE GETTER ICI :
    public Long getId() {
        return id;
    }

    // Le reste des Getters et Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}