package com.emporio.emporio.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotBlank;

import com.emporio.emporio.dto.AttivitaDescrizioneGetDto;
import com.emporio.emporio.dto.AttivitaGetDto;
import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.AttivitaDescrizione;
import com.emporio.emporio.model.User;
import com.emporio.emporio.services.DipendenteService;
import com.emporio.emporio.services.RoleService;
import com.emporio.emporio.services.TitolareService;
import com.emporio.emporio.services.UserService;

import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private DipendenteService employeeService;

    @Autowired
    private TitolareService titolareService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/users/{username}")
    public ResponseEntity<Boolean> existsUser(@NotBlank @PathVariable(name = "username", required = true) String username) {
        boolean exists = userService.existsUser(username);
        return (exists) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/users/{username}/shops")
    public ResponseEntity<AttivitaDescrizioneGetDto> getShop(@NotBlank @PathVariable(name = "username", required = true) String username) {
        boolean isDipendente = employeeService.existsDipendente(username);
        boolean isTitolare = titolareService.existsTitolare(username);

        Attivita shop;
        if (isTitolare) {
            shop = titolareService.getShopOwnedBy(username);
        } else if (isDipendente) {
            shop = employeeService.getShopEmployedIn(username);
        } else {
            return null;
        }

        return ResponseEntity.ok(this.convertToDto(shop.getShopDescription()));
    }

    private AttivitaDescrizioneGetDto convertToDto(AttivitaDescrizione shopDescription) {
        return this.modelMapper.map(shopDescription, AttivitaDescrizioneGetDto.class);
    }
}