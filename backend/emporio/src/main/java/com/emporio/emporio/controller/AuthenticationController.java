package com.emporio.emporio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.emporio.emporio.dto.AuthenticationRequest;
import com.emporio.emporio.factory.UserFactory;
import com.emporio.emporio.model.Role;
import com.emporio.emporio.repository.RoleRepository;
import com.emporio.emporio.repository.UserRepository;
import com.emporio.emporio.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    RoleRepository roles;

    @Autowired
    UserRepository users;

    @Autowired
    PasswordEncoder passwordEncoder;

    @SuppressWarnings("rawtypes")
    @PostMapping("/auth/signin")
    public ResponseEntity signin(@Valid @RequestBody AuthenticationRequest data) {

        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            Role role = this.users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRole();
            String token = jwtTokenProvider.createToken(username, role);

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
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

            boolean userExists = this.users.findByUsername(username).isPresent();

            if (userExists) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }

            Role role = this.roles.findByNameIgnoreCase(data.getRole()).orElseThrow(() -> new NoSuchElementException("Role " + data.getRole() + "not found"));

            UserFactory factory = Class.forName("com.emporio.emporio.factory." + role.getName() + "UserFactory").asSubclass(UserFactory.class).getDeclaredConstructor().newInstance();

            this.users.save(factory.createUser(username, this.passwordEncoder.encode(data.getPassword()), roles));

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}