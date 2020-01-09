package com.emporio.emporio.factory;

import java.util.function.Function;

import com.emporio.emporio.model.Titolare;
import com.emporio.emporio.model.Role;
import com.emporio.emporio.model.User;

public class TitolareUserFactory implements UserFactory {
    public User createUser(String username, String password, Function<String, Role> role) {
        return new Titolare(username, password, role.apply("Titolare"));
    }
}