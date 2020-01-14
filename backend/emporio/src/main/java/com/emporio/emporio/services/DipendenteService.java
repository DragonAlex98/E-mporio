package com.emporio.emporio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.Dipendente;
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

    public boolean existsDipendente(String username) {
        return employeeRepository.existsByUsername(username);
    }

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

    public Attivita getShopEmployedIn(String username) {
        Dipendente employee = this.getDipendente(username);

        if(employee.getShopEmployed() == null)
            throw new EntityNotFoundException("Il dipendente " + username + " non ha attività associate!");

        return employee.getShopEmployed();
    }

    public Dipendente addShopEmployed(Attivita shop, Dipendente employee) {
        if(this.employeeAlreadyWorks(employee))
            throw new EntityExistsException("L'utente è già dipendente in una azienda!");

        employee.setShopEmployed(shop);
        return employeeRepository.save(employee);
    }

    public void detachShopFromEmployees(List<Dipendente> employees) {
        if(employees != null)
            employees.stream().forEach(employee -> employee.setShopEmployed(null));
    }
}