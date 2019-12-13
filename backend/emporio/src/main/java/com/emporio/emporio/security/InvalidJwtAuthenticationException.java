package com.emporio.emporio.security;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {
    private static final long serialVersionUID = -6620082044115793468L;

    public InvalidJwtAuthenticationException(String e) {
        super(e);
    }
}