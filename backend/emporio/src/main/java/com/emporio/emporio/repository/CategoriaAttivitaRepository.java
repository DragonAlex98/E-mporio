package com.emporio.emporio.repository;

import java.util.Optional;

import com.emporio.emporio.model.CategoriaAttivita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaAttivitaRepository extends JpaRepository<CategoriaAttivita, Integer> {

    Optional<CategoriaAttivita> findByShopCategoryDescription(String description);

    boolean existsByShopCategoryDescription(String description);
    
}