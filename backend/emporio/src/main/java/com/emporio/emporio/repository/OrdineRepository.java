package com.emporio.emporio.repository;

import java.util.Optional;

import com.emporio.emporio.model.Ordine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Integer> {
   
    Optional<Ordine> findByOrderId(Long id);
    
    boolean existsOrdineByOrderId(Long id);

}