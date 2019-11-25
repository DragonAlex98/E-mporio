package com.emporio.emporio.Repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.emporio.emporio.Models.Attivita;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class AttivitaRepositoryImpl implements AttivitaRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Attivita> findAttivita(String pIva, String ragSociale, Integer categoria, String sedeOperativa) {
        String correctPIva = (pIva == "") ? null : pIva;
        String correctRagSociale = ragSociale.toLowerCase();
        String correctSedeOperativa = (sedeOperativa == "") ? null : sedeOperativa;
        Integer correctCategoria = (categoria == 0) ? null : categoria;

        TypedQuery<Attivita> query = entityManager.createQuery("SELECT * FROM emporio.attivita WHERE nome LIKE ?" 
                                                        + ((correctPIva != null) ? ("AND id = " + correctPIva) : "")
                                                        + ((correctSedeOperativa != null) ? ("AND prezzo = " + correctSedeOperativa) : "")
                                                        + ((correctCategoria != null) ? ("AND categoria = " + correctCategoria) : "") 
                                                        , Attivita.class);

        query.setParameter(1, "%" + correctRagSociale + "%");

        return query.getResultList();
    }

    
}