package com.example.trabalhoiago.repository;

import com.example.trabalhoiago.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
