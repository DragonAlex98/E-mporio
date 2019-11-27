package com.emporio.emporio.Services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.emporio.emporio.Models.Prodotto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProdottoRepositoryImpl implements ProdottoRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Prodotto> findProduct(String nome, Double prezzo, Integer categoria) {
        String correctNome = nome.toLowerCase();
        Double correctPrezzo = (prezzo == 0) ? null : prezzo;
        Integer correctCategoria = (categoria == 0) ? null : categoria;

        TypedQuery<Prodotto> query = entityManager.createQuery("SELECT p FROM Prodotto p WHERE nome LIKE ?1 " 
                                                        + ((correctPrezzo != null) ? "AND prezzo = ?2 " : "AND prezzo LIKE ?2")
                                                        + ((correctCategoria != null) ? "AND categoria = ?3 " : "AND categoria LIKE ?3 ") 
                                                        , Prodotto.class);

        return query.setParameter(1, "%" + correctNome + "%").setParameter(2, ((correctPrezzo != null) ? correctPrezzo : "%")).setParameter(3, ((correctCategoria != null) ? correctCategoria : "%")).getResultList();
    }

    
}