package com.emporio.emporio.repository;

import java.util.List;
import java.util.Optional;

import com.emporio.emporio.model.Attivita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttivitaRepository extends JpaRepository<Attivita, String> {
    
    Optional<Attivita> findByShopDescription_shopPIVA(String pIva);

    boolean existsAttivitaByShopDescription_shopPIVA(String shopPIVA);

    List<Attivita> findByShopDescription_shopBusinessNameContaining(String ragioneSociale);
    
    void deleteByShopDescription_shopPIVA(String shopPIVA);
}