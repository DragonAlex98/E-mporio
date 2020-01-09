package com.emporio.emporio.factory;

import java.util.function.Function;

import com.emporio.emporio.model.Role;
import com.emporio.emporio.model.User;

public interface UserFactory {

    abstract User createUser(String username, String password, Function<String, Role> role);
}