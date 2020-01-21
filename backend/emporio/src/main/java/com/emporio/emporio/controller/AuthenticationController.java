package com.emporio.emporio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.emporio.emporio.dto.AuthenticationRequest;
import com.emporio.emporio.dto.RefreshTokenDto;
import com.emporio.emporio.factory.UserFactory;
import com.emporio.emporio.model.Role;
import com.emporio.emporio.security.InvalidJwtAuthenticationException;
import com.emporio.emporio.security.JwtTokenProvider;
import com.emporio.emporio.services.RoleService;
import com.emporio.emporio.services.UserService;
import com.emporio.emporio.util.ApiPostResponse;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @SuppressWarnings("rawtypes")
    @PostMapping("/auth/signin")
    public ResponseEntity signin(@Valid @RequestBody AuthenticationRequest data) {

        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            Role role = userService.getUser(username).getRole();
            String token = jwtTokenProvider.createToken(username, role.getName());
            String refreshToken = jwtTokenProvider.createRefreshToken(username, role.getName());

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            model.put("refresh", refreshToken);
            model.put("role", role.getName());
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/auth/signup")
    public ResponseEntity signup(@Valid @RequestBody AuthenticationRequest data) throws Exception {

        try {
            String username = data.getUsername();

            Role role = this.roleService.getRole(data.getRole());

            if (role.getName().equalsIgnoreCase("Admin") || role.getName().equalsIgnoreCase("OperatoreSistema")) {
                return ResponseEntity.badRequest().body(ApiPostResponse.builder().message("Non si possiedono privilegi sufficienti!").build());
            }

            UserFactory factory = Class.forName("com.emporio.emporio.factory." + role.getName() + "UserFactory").asSubclass(UserFactory.class).getDeclaredConstructor().newInstance();
            this.userService.createUser(factory.createUser(username, this.passwordEncoder.encode(data.getPassword()), this.roleService::getRole));

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @PostMapping("/auth/refresh")
    public @ResponseBody String refresh(@Valid @RequestBody RefreshTokenDto refreshToken) throws Exception {
        try {
            return jwtTokenProvider.refreshToken(refreshToken.getRefresh());
        } catch (AuthenticationException e) {
            throw new InvalidJwtAuthenticationException("Invalid or expired token supplied");
        }
    }
}