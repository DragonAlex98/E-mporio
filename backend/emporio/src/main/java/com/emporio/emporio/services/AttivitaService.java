package com.emporio.emporio.services;

import java.util.Optional;

import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.repository.AttivitaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AttivitaService
 */
@Service
public class AttivitaService {

    @Autowired
    private AttivitaRepository shopRepository;

    public boolean existsAttivita(String pIva) {
        if(!shopRepository.existsAttivitaByShopPIVA(pIva))
            return false;

        return true;
    }
    
}