package com.emporio.emporio.Repositories;

import java.util.List;

import com.emporio.emporio.Models.Prodotto;

public interface ProdottoRepositoryCustom {

    List<Prodotto> findProduct(Integer id, String nome, Double prezzo, Integer categoria);

}