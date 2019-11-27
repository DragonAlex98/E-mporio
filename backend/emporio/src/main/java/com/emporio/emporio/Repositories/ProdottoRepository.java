package com.emporio.emporio.Repositories;

import com.emporio.emporio.Models.Prodotto;
import com.emporio.emporio.Services.ProdottoRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer>, ProdottoRepositoryCustom{
   
}