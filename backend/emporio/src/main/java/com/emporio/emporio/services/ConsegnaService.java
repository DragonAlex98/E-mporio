package com.emporio.emporio.services;

import com.emporio.emporio.model.Consegna;
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
    
}