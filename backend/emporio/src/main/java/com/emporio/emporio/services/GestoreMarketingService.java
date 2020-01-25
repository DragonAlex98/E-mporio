package com.emporio.emporio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.GestoreMarketing;
import com.emporio.emporio.repository.GestoreMarketingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * GestoreMarketingService
 */
@Service
public class GestoreMarketingService {

    @Autowired
    private GestoreMarketingRepository marketingRepository;

    public boolean existsGestoreMarketing(String username) {
        return marketingRepository.existsByUsername(username);
    }

    public GestoreMarketing getGestoreMarketing(String username) {
        Optional<GestoreMarketing> gestore = marketingRepository.findByUsername(username);

        if(!gestore.isPresent())
            throw new EntityNotFoundException("Lo username " + username + " non è registrato nel sistema o non è un gestore marketing!");

        return gestore.get();
    }

    public boolean managerAlreadyWorks(GestoreMarketing manager) {
        return manager.getShopWorksFor() != null;
    }

    public GestoreMarketing saveManager(GestoreMarketing manager) {
        return marketingRepository.save(manager);
    }

    public Attivita getShopWorksFor(String username) {
        GestoreMarketing gestore = this.getGestoreMarketing(username);

        if(gestore.getShopWorksFor() == null)
            throw new EntityNotFoundException("Il gestore marketing " + username + " non ha attività associate!");

        return gestore.getShopWorksFor();
    }

    public GestoreMarketing addShopManager(Attivita shop, GestoreMarketing manager) {
        if(this.managerAlreadyWorks(manager))
            throw new EntityExistsException("L'utente è già gestore marketing in una azienda!");

        manager.setShopWorksFor(shop);
        return marketingRepository.save(manager);
    }

    public void detachShopFromMarketingManagers(List<GestoreMarketing> managers) {
        if(managers != null) {
            managers.stream().forEach(manager -> manager.setShopWorksFor(null));
        }
    }
}