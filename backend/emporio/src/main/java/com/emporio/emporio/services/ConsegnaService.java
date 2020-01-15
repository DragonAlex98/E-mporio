package com.emporio.emporio.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Consegna;
import com.emporio.emporio.model.Fattorino;
import com.emporio.emporio.repository.ConsegnaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * ConsegnaService
 */
@Service
public class ConsegnaService {
    
    @Autowired
    private ConsegnaRepository consegnaRepository;

    public Consegna saveConsegna(Consegna consegna) {
        return consegnaRepository.save(consegna);
    }

    public List<Consegna> getDeliveryByFattorino (Fattorino fattorino) {

        List<Consegna> deliveryList = this.consegnaRepository.findByfattorino_id(fattorino.getId());

        if (deliveryList.size() == 0) throw new EntityNotFoundException("Non ci sono consegne da visualizzare");

        return deliveryList;

    }
    
}