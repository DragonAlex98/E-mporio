package com.emporio.emporio.services;

import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.User;
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

    public User getUser(String username) {
        Optional<User> employeeOptional = userRepository.findByUsername(username);

        //Controllo se lo username dipendente passato esiste nel sistema
        if(!employeeOptional.isPresent())
            throw new EntityNotFoundException("User " + username + " non trovato!");

        return employeeOptional.get();
    }
}