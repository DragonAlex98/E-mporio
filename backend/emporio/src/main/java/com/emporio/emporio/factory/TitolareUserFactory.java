package com.emporio.emporio.factory;

import com.emporio.emporio.model.Titolare;
import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.RoleRepository;

public class TitolareUserFactory implements UserFactory {
    public User createUser(String username, String password, RoleRepository roleRepository) {
        return new Titolare(username, password, roleRepository.findByNameIgnoreCase("Titolare").get());
    }
}