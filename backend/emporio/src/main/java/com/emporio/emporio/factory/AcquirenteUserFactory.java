package com.emporio.emporio.factory;

import com.emporio.emporio.model.Acquirente;
import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.RoleRepository;

public class AcquirenteUserFactory implements UserFactory {
    public User createUser(String username, String password, RoleRepository roleRepository) {
        return new Acquirente(username, password, roleRepository.findByName("Acquirente").get());
    }
}