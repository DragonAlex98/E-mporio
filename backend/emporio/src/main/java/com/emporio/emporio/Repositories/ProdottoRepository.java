package com.emporio.emporio.Repositories;

import com.emporio.emporio.Models.Prodotto;
import com.emporio.emporio.services.ProdottoRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer>, ProdottoRepositoryCustom{
    
    boolean existsByProductName(String nome);
   
}