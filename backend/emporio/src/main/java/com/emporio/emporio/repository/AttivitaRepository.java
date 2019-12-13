package com.emporio.emporio.repository;

import java.util.List;

import com.emporio.emporio.model.Attivita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttivitaRepository extends JpaRepository<Attivita, String> {

    boolean existsAttivitaByShopPIVA(String shopPIVA);

    List<Attivita> findByShopBusinessNameContaining(String ragioneSociale);
    
}