package com.emporio.emporio.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Role;
import com.emporio.emporio.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RoleService
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getRole(String roleName) {
        Optional<Role> optionalRole = roleRepository.findByNameIgnoreCase(roleName);

        if(!optionalRole.isPresent())
            throw new EntityNotFoundException("Ruolo " + roleName + " non trovato!");

        return optionalRole.get();
    }
}