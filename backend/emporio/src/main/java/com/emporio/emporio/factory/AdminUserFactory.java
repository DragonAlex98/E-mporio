package com.emporio.emporio.factory;

import com.emporio.emporio.model.Admin;
import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.RoleRepository;

public class AdminUserFactory implements UserFactory {
    public User createUser(String username, String password, RoleRepository roleRepository) {
        return new Admin(username, password, roleRepository.findByNameIgnoreCase("Admin").get());
    }
}