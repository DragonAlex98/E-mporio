package com.emporio.emporio.repository;

import com.emporio.emporio.model.Dipendente;

import org.springframework.stereotype.Repository;

@Repository
public interface DipendenteRepository extends UserBaseRepository<Dipendente> {
    
}