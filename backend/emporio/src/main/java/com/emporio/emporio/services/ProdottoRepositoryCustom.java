package com.emporio.emporio.services;

import java.util.List;

import com.emporio.emporio.Models.Prodotto;

import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepositoryCustom {

    List<Prodotto> findProduct(Integer id, String nome, Double prezzo, Integer categoria);

}