package com.emporio.emporio.factory;

import java.util.function.Function;

import com.emporio.emporio.model.GestoreMarketing;
import com.emporio.emporio.model.Role;
import com.emporio.emporio.model.User;

public class GestoreMarketingUserFactory implements UserFactory {
    public User createUser(String username, String password, Function<String, Role> role) {
        return new GestoreMarketing(username, password, role.apply("GestoreMarketing"));
    }
}