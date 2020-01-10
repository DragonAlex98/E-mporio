package com.emporio.emporio.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Acquirente;
import com.emporio.emporio.repository.AcquirenteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AcquirenteService
 */
@Service
public class AcquirenteService {

    @Autowired
    private AcquirenteRepository customerRepository;

    public Acquirente getAcquirente(String username) {
        Optional<Acquirente> employee = customerRepository.findByUsername(username);

        if(!employee.isPresent())
            throw new EntityNotFoundException("Lo username " + username + " non è registrato nel sistema o non è un'acquirente!");

        return employee.get();
    }
}