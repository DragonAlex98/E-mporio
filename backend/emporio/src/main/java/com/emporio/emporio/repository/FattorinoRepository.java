package com.emporio.emporio.repository;

import com.emporio.emporio.model.Fattorino;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FattorinoRepository extends JpaRepository<Fattorino, Long> {
    
}