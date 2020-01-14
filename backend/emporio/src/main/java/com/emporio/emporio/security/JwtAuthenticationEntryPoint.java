package com.emporio.emporio.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // Questo metodo e' invocato quando un utente tenta di accedere ad un endpoint nn pubblico senza credenziali corrette
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}