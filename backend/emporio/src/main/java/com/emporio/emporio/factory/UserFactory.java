package com.emporio.emporio.factory;

import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.RoleRepository;

public interface UserFactory {
    abstract User createUser(String username, String password, RoleRepository roleRepository);
}