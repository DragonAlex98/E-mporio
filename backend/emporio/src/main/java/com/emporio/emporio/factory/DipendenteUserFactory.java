package com.emporio.emporio.factory;

import java.util.function.Function;

import com.emporio.emporio.model.Dipendente;
import com.emporio.emporio.model.Role;
import com.emporio.emporio.model.User;

public class DipendenteUserFactory implements UserFactory {
    public User createUser(String username, String password, Function<String, Role> role) {
        return new Dipendente(username, password, role.apply("Dipendente"));
    }
}