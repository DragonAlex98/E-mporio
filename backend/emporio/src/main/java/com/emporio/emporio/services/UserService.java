package com.emporio.emporio.services;

import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Role;
import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.RoleRepository;
import com.emporio.emporio.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User getUser(String username) {
        Optional<User> employeeOptional = userRepository.findByUsername(username);

        //Controllo se lo username dipendente passato esiste nel sistema
        if(!employeeOptional.isPresent())
            throw new EntityNotFoundException("User " + username + " non trovato!");

        return employeeOptional.get();
    }

    public Role getRole(String role) {
        Optional<Role> roleOptional = roleRepository.findByNameIgnoreCase(role);

        if(!roleOptional.isPresent())
            throw new EntityNotFoundException("Ruolo " + role + " non trovato!");

        return roleOptional.get();
    }

    public boolean isDipendente(String username) {
        User user = this.getUser(username);
        return isRole(user, "Dipendente");
    }

    public boolean isDipendente(User user) {
        return isRole(user, "Dipendente");
    }

    /* public boolean isTitolare(String username) {
        User user = this.getUser(username);
        return isRole(user, "Titolare");
    }

    public boolean isTitolare(User user) {
        return isRole(user, "Titolare");
    }

    public boolean isAcquirente(String username) {
        User user = this.getUser(username);
        return isRole(user, "Acquirente");
    }

    public boolean isAcquirente(User user) {
        return isRole(user, "Acquirente");
    } */

    private boolean isRole(User user, String role) {
        return (user.getClass().getSimpleName().equals(this.getRole(role).getName()));
    }
}