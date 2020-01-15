package com.emporio.emporio.repository;

import java.util.List;
import java.util.Optional;

import com.emporio.emporio.model.Prodotto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ProdottoRepository
 */
@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long>{

    Optional<Prodotto> findByProductDescription_productName(String productName);
    
    List<Prodotto> findAllByProductDescription_productNameContaining(String productName);
}