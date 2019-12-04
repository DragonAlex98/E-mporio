package com.emporio.emporio.Repositories;

import com.emporio.emporio.Models.Attivita;
import com.emporio.emporio.services.AttivitaRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttivitaRepository extends JpaRepository<Attivita, String>, AttivitaRepositoryCustom {

    boolean existsAttivitaByShopPIVA(String shopPIVA);
    
}