package com.emporio.emporio.services;

import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.Dipendente;
import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.DipendenteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DipendenteService
 */
@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository employeeRepository;

    public Dipendente getDipendente(String username) {
        Optional<Dipendente> employee = employeeRepository.findByUsername(username);

        if(!employee.isPresent())
            throw new EntityNotFoundException("Lo username " + username + " non è registrato nel sistema o non è un dipendente!");

        return employee.get();
    }

    public boolean employeeAlreadyWorks(Dipendente employee) {
        return employee.getShopEmployed() != null;
    }

    public Dipendente saveDipendente(Dipendente employee) {
        return employeeRepository.save(employee);
    }

    public Dipendente addShopEmployed(Attivita shop, Dipendente employee) {
        if(this.employeeAlreadyWorks(employee))
            throw new EntityExistsException("L'utente è già dipendente in una azienda!");

        employee.setShopEmployed(shop);
        return employeeRepository.save(employee);
    }

    public boolean isDipendente(User user) {
        if(!(user instanceof Dipendente))
            throw new EntityNotFoundException("Lo user " + user.getUsername() + " non è un dipendente!");

        return true;
    }
    
}