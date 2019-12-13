package com.emporio.emporio.repository;

import java.util.List;

import com.emporio.emporio.model.Prodotto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {
    
    boolean existsByProductName(String nome);

    List<Prodotto> findByProductNameContaining(String name);
   
}