package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Prospect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String gender;
    private String status;
    private String location;
    private String type;

    private boolean verified = false;

    // getters & setters
}