package com.emporio.emporio.factory;

import java.util.function.Function;

import com.emporio.emporio.model.Admin;
import com.emporio.emporio.model.Role;
import com.emporio.emporio.model.User;

public class AdminUserFactory implements UserFactory {
    public User createUser(String username, String password, Function<String, Role> role) {
        return new Admin(username, password, role.apply("Admin"));
    }
}