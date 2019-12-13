package com.emporio.emporio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emporio.emporio.model.Catalogo;

@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Long> {
    
}