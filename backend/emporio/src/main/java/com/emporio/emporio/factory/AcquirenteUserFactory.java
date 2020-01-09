package com.emporio.emporio.factory;

import java.util.function.Function;

import com.emporio.emporio.model.Acquirente;
import com.emporio.emporio.model.Role;
import com.emporio.emporio.model.User;

public class AcquirenteUserFactory implements UserFactory {
    public User createUser(String username, String password, Function<String, Role> role) {
        return new Acquirente(username, password, role.apply("Acquirente"));
    }
}