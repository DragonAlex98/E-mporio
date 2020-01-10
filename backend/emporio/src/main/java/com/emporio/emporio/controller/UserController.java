package com.emporio.emporio.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotBlank;

import com.emporio.emporio.dto.AttivitaGetDto;
import com.emporio.emporio.model.User;
import com.emporio.emporio.services.RoleService;
import com.emporio.emporio.services.UserService;

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
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/users/{username}")
    public ResponseEntity<Boolean> existsUser(@NotBlank @PathVariable(name = "username", required = true) String username) {
        boolean exists = userService.existsUser(username);
        return (exists) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/users/{username}/shops")
    public ResponseEntity<AttivitaGetDto> getShop(@NotBlank @PathVariable(name = "username", required = true) String username) {
        User user = userService.getUser(username);

        AttivitaGetDto shopDto;
        if (userService.hasRole(user, roleService.getRole("Titolare"))) {
            shopDto = AttivitaGetDto.parseAttivitaToAttivitaGetDto(user.getShopOwned());
        } else if (userService.hasRole(user, roleService.getRole("Dipendente"))) {
            shopDto = AttivitaGetDto.parseAttivitaToAttivitaGetDto(user.getShopEmployed());
        } else {
            throw new EntityNotFoundException("L'utente " + username + " non è nè un titolare nè un dipendente");
        }

        if (shopDto == null) {
            throw new EntityNotFoundException("Il negozio associato all'utente " + username + " non esiste");
        }

        return ResponseEntity.ok(shopDto);
    }
}