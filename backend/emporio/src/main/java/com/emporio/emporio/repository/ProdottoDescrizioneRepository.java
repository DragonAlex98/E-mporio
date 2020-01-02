package com.emporio.emporio.repository;

import java.util.List;
import java.util.Optional;

import com.emporio.emporio.model.ProdottoDescrizione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdottoDescrizioneRepository extends JpaRepository<ProdottoDescrizione, Integer> {
    
    boolean existsByProductName(String nome);

    List<ProdottoDescrizione> findByProductNameContaining(String name);

    Optional<ProdottoDescrizione> findByProductName(String name);
   
}