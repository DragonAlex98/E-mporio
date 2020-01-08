package com.emporio.emporio.services;

import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Titolare;

import org.springframework.stereotype.Service;

/**
 * TitolareService
 */
@Service
public class TitolareService {

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
        //TODO
        return null;
    }
    
}