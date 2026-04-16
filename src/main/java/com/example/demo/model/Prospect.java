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

    public String getName() { return name; }
public void setName(String name) { this.name = name; }

public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }

public String getPhone() { return phone; }
public void setPhone(String phone) { this.phone = phone; }

public String getGender() { return gender; }
public void setGender(String gender) { this.gender = gender; }

public String getStatus() { return status; }
public void setStatus(String status) { this.status = status; }

public String getLocation() { return location; }
public void setLocation(String location) { this.location = location; }

public String getType() { return type; }
public void setType(String type) { this.type = type; }

public boolean isVerified() { return verified; }
public void setVerified(boolean verified) { this.verified = verified; }
}