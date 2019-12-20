package com.emporio.emporio.repository;

import java.util.Optional;

import com.emporio.emporio.model.CategoriaProdotto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaProdottoRepository extends JpaRepository<CategoriaProdotto, Integer> {
    
    Optional<CategoriaProdotto> findByDescription(String description);

    boolean existsByDescription(String description);
}