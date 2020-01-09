package com.emporio.emporio.factory;

import java.util.function.Function;

import com.emporio.emporio.model.Fattorino;
import com.emporio.emporio.model.Role;
import com.emporio.emporio.model.User;

public class FattorinoUserFactory implements UserFactory {
    public User createUser(String username, String password, Function<String, Role> role) {
        return new Fattorino(username, password, role.apply("Fattorino"));
    }
}