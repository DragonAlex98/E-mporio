package com.emporio.emporio.Repositories;


import com.emporio.emporio.Models.Prodotto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends CrudRepository<Prodotto, Integer>{

    
}