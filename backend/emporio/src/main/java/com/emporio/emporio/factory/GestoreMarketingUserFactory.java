package com.emporio.emporio.factory;

import com.emporio.emporio.model.GestoreMarketing;
import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.RoleRepository;

public class GestoreMarketingUserFactory implements UserFactory {
    public User createUser(String username, String password, RoleRepository roleRepository) {
        return new GestoreMarketing(username, password, roleRepository.findByName("GestoreMarketing").get());
    }
}