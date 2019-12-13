package com.emporio.emporio.repository;

import com.emporio.emporio.model.CategoriaProdotto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaProdottoRepository extends JpaRepository<CategoriaProdotto, Integer> {

    boolean existsByDescription(String description);
    
}