package com.emporio.emporio.factory;

import com.emporio.emporio.model.OperatoreSistema;
import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.RoleRepository;

public class OperatoreSistemaUserFactory implements UserFactory {
    public User createUser(String username, String password, RoleRepository roleRepository) {
        return new OperatoreSistema(username, password, roleRepository.findByName("OperatoreSistema").get());
    }
}