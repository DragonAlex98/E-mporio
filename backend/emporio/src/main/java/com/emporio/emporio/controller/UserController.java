package com.emporio.emporio.controller;

import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRepository users;

    @GetMapping("/users")
    public ResponseEntity<Boolean> existsUser(@RequestBody User user) {
        boolean exists = users.existsByUsername(user.getUsername());
        return (exists) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}