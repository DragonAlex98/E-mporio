package com.emporio.emporio.controller;

import javax.validation.constraints.NotBlank;

import com.emporio.emporio.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRepository users;

    @GetMapping("/users/{username}")
    public ResponseEntity<Boolean> existsUser(@NotBlank @PathVariable(name = "username", required = true) String username) {
        boolean exists = users.existsByUsername(username);
        return (exists) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}