package com.emporio.emporio.Repositories;

import com.emporio.emporio.Models.CategoriaProdotto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepository extends JpaRepository<CategoriaProdotto, Integer> {

    boolean existsByDescription(String description);
    
}