package com.emporio.emporio.factory;

import com.emporio.emporio.model.Fattorino;
import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.RoleRepository;

public class FattorinoUserFactory implements UserFactory {
    public User createUser(String username, String password, RoleRepository roleRepository) {
        return new Fattorino(username, password, roleRepository.findByNameIgnoreCase("Fattorino").get());
    }
}