package com.emporio.emporio.services;

import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.Titolare;
import com.emporio.emporio.repository.TitolareRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TitolareService
 */
@Service
public class TitolareService {

    @Autowired
    private TitolareRepository ownerRepository;

    public boolean existsTitolare(String username) {
        return ownerRepository.existsByUsername(username);
    }

    public boolean hasShop(Titolare titolare) {
        if(titolare.getShopOwned() == null)
            return false;

        return true;
    }

    public boolean hasShop(String usernameTitolare) {
        Titolare titolare = this.getTitolare(usernameTitolare);
        return this.hasShop(titolare);
    }

    public Titolare getTitolare(String usernameTitolare) {
        Optional<Titolare> owner = ownerRepository.findByUsername(usernameTitolare);

        if(!owner.isPresent())
            throw new EntityNotFoundException("User " + usernameTitolare + " non trovato!");

        return owner.get();
    }

    public Attivita getShopOwnedBy(String username) {
        Titolare owner = this.getTitolare(username);

        if(owner.getShopOwned() == null)
            throw new EntityNotFoundException("Il titolare " + username + " non ha attività associate!");

        return owner.getShopOwned();
    }

    public void detachShopFromOwner(String username) {
        Titolare owner = this.getTitolare(username);
        owner.setShopOwned(null);
        ownerRepository.save(owner);
    }

    public void checkOnShopAdd(Titolare titolare) {
        if(this.hasShop(titolare))
            throw new EntityExistsException("Il titolare " + titolare.getUsername() + " ha già un negozio associato");   
    }

    public Titolare setShopOwnedBy(Titolare owner, Attivita shop) {
        owner.setShopOwned(shop);
        return this.ownerRepository.save(owner);
    }
    
}