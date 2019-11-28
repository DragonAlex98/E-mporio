package com.emporio.emporio.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.emporio.emporio.Models.Attivita;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AttivitaRepositoryImpl implements AttivitaRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Attivita> findAttivita(String ragSociale, Integer categoria, String sedeOperativa) {
        String correctRagSociale = ragSociale.toLowerCase();
        String correctSedeOperativa = (sedeOperativa.equals("")) ? null : sedeOperativa;
        Integer correctCategoria = (categoria == 0) ? null : categoria;

        TypedQuery<Attivita> query = entityManager.createQuery("SELECT a FROM Attivita a WHERE ragione_sociale LIKE ?1 " 
                                                        + ((correctSedeOperativa != null) ? "AND sede_operativa = ?2 " : "AND sede_operativa LIKE ?2")
                                                        + ((correctCategoria != null) ? "AND categoria = ?3 " : "AND categoria LIKE ?3 ") 
                                                        , Attivita.class);

        return query.setParameter(1, "%" + correctRagSociale + "%").setParameter(2, ((correctSedeOperativa != null) ? correctSedeOperativa : "%")).setParameter(3, ((correctCategoria != null) ? correctCategoria : "%")).getResultList();
    }

    
}