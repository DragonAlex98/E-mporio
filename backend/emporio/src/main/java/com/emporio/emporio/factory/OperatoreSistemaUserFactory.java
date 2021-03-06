package com.emporio.emporio.factory;

import java.util.function.Function;

import com.emporio.emporio.model.OperatoreSistema;
import com.emporio.emporio.model.Role;
import com.emporio.emporio.model.User;

public class OperatoreSistemaUserFactory implements UserFactory {
    public User createUser(String username, String password, Function<String, Role> role) {
        return new OperatoreSistema(username, password, role.apply("OperatoreSistema"));
    }
}