package com.emporio.emporio.Repositories;

import com.emporio.emporio.Models.Attivita;
import com.emporio.emporio.Services.AttivitaRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttivitaRepository extends JpaRepository<Attivita, String>, AttivitaRepositoryCustom {

    
}