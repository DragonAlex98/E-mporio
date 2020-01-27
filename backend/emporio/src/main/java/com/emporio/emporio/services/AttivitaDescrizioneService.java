package com.emporio.emporio.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.AttivitaDescrizione;
import com.emporio.emporio.repository.AttivitaDescrizioneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AttivitaDescrizioneService
 */
@Service
public class AttivitaDescrizioneService {

    @Autowired
    private AttivitaDescrizioneRepository shopDescRepo;

    public void detachAttivitaFrom(AttivitaDescrizione shopDescription) {
        shopDescription.setShop(null);
        this.shopDescRepo.save(shopDescription);
    }

    public AttivitaDescrizione getAttivitaDescrizione(String piva) {
        Optional<AttivitaDescrizione> shopDesc = shopDescRepo.findById(piva);

        if(!shopDesc.isPresent())
            throw new EntityNotFoundException("L'attività descrizione con partita iva " + piva + "non è registrata nel sistema!");

        return shopDesc.get();
    }    
}