package com.emporio.emporio.repository;

import com.emporio.emporio.model.Dipendente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    
}