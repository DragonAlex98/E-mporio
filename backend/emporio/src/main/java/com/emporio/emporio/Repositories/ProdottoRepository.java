package com.emporio.emporio.Repositories;


import javax.persistence.Query;

import com.emporio.emporio.Models.Prodotto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer>, ProdottoRepositoryCustom{


    
}