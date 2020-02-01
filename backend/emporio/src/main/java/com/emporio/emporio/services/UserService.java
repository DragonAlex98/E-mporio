package com.emporio.emporio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Role;
import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean existsUser(String username) {
        return userRepository.existsByUsername(username);
    }

    public User getUser(String username) {
        Optional<User> employeeOptional = userRepository.findByUsername(username);

        //Controllo se lo username dipendente passato esiste nel sistema
        if(!employeeOptional.isPresent())
            throw new EntityNotFoundException("User " + username + " non trovato!");

        return employeeOptional.get();
    }

    public boolean hasRole(User user, Role role) {
        return (user.getRole().getName().equalsIgnoreCase(role.getName()));
    }

    public User createUser(User user) {
        if (existsUser(user.getUsername())) {
            throw new EntityExistsException("User " + user.getUsername() + " esiste già!");
        }

        return userRepository.save(user);
    }

    public User updateUser(User user) {
        if (!existsUser(user.getUsername())) {
            throw new EntityNotFoundException("User " + user.getUsername() + "  non esiste!");
        }
        
        return userRepository.save(user);
    }

    public List<User> getUsers(String username) {
        return userRepository.findByUsernameContainingIgnoreCase(username);
    }

    public boolean changePassword(String username, String oldPassword, String newPassword, String confirmNewPassword) {
        User user = this.getUser(username);
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return false;
        }
        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            return false;
        }
        if (!newPassword.equals(confirmNewPassword)) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        this.updateUser(user);
        return true;
    }
}