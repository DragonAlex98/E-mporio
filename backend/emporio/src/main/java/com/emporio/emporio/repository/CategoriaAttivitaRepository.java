package com.emporio.emporio.repository;

import com.emporio.emporio.model.CategoriaAttivita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaAttivitaRepository extends JpaRepository<CategoriaAttivita, Integer> {

    boolean existsByShopCategoryDescription(String description);
    
}