package com.emporio.emporio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Consegna;
import com.emporio.emporio.model.Fattorino;
import com.emporio.emporio.model.StatoConsegna;
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

    public Consegna getDeliveryById(Integer id) {

       Optional<Consegna> consegnaOpt = consegnaRepository.findById(id);

       if (!consegnaOpt.isPresent()) {throw new EntityNotFoundException("Consegna non trovata!");}

       Consegna consegna = consegnaOpt.get();

       return consegna;

    }

    public Consegna changeDeliveryStatus(Consegna delivery, Integer statusId) {
        switch (statusId) {
            case 1:
                delivery.setStatoConsegna(StatoConsegna.CONSEGNATA);
                break;
            case 2:
                delivery.setStatoConsegna(StatoConsegna.RITIRATA);
                break;
            default:
                throw new EntityNotFoundException("Lo stato consegna con id: " + statusId + " è inesistente!");
        }

        return this.consegnaRepository.save(delivery);
    }
    
}