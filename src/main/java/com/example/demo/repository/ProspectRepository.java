package com.example.demo.repository;

import com.example.demo.model.Prospect;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProspectRepository extends JpaRepository<Prospect, Long> {
    Optional<Prospect> findByEmail(String email);
}