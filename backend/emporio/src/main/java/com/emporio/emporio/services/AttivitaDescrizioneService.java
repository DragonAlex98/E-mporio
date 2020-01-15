package com.emporio.emporio.services;

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
    
}