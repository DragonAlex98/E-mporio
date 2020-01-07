package com.emporio.emporio.factory;

import com.emporio.emporio.model.Dipendente;
import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.RoleRepository;

public class DipendenteUserFactory implements UserFactory {
    public User createUser(String username, String password, RoleRepository roleRepository) {
        return new Dipendente(username, password, roleRepository.findByNameIgnoreCase("Dipendente").get());
    }
}