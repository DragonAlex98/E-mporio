package com.emporio.emporio.repository;

import com.emporio.emporio.model.Acquirente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquirenteRepository extends JpaRepository<Acquirente, Long> {
    
}