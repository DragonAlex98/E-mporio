package com.emporio.emporio.controller;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import com.emporio.emporio.dto.AttivitaGetDto;
import com.emporio.emporio.model.User;
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

    @GetMapping("/users/{username}/shops")
    public ResponseEntity<AttivitaGetDto> getShop(@NotBlank @PathVariable(name = "username", required = true) String username) {
        Optional<User> userOpt = users.findByUsername(username);

        if(!userOpt.isPresent())
            return ResponseEntity.badRequest().body(null);

        User user = userOpt.get();

        if(!user.getRole().getName().equals("Titolare") && !user.getRole().getName().equals("Dipendente")) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(AttivitaGetDto.parseAttivitaToAttivitaGetDto(user.getShopEmployed()));
    }
}