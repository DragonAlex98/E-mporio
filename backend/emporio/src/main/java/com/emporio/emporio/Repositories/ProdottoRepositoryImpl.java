package com.emporio.emporio.Repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.emporio.emporio.Models.Prodotto;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class ProdottoRepositoryImpl implements ProdottoRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Prodotto> findProduct(Integer id, String nome, Double prezzo, Integer categoria) {
        Integer correctId = (id == 0) ? null : id;
        String correctNome = nome.toLowerCase();
        Double correctPrezzo = (prezzo == 0) ? null : prezzo;
        Integer correctCategoria = (categoria == 0) ? null : categoria;

        TypedQuery<Prodotto> query = entityManager.createQuery("SELECT * FROM emporio.prodotto WHERE nome LIKE ?" 
                                                        + ((correctId != null) ? ("AND id = " + correctId) : "")
                                                        + ((correctPrezzo != null) ? ("AND prezzo = " + correctPrezzo) : "")
                                                        + ((correctCategoria != null) ? ("AND categoria = " + correctCategoria) : "") 
                                                        , Prodotto.class);

        query.setParameter(1, "%" + correctNome + "%");

        return query.getResultList();
    }

    
}