package com.emporio.emporio.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.emporio.emporio.dto.AttivitaDescrizioneGetDto;
import com.emporio.emporio.dto.OrdineHistoryDto;
import com.emporio.emporio.model.Acquirente;
import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.AttivitaDescrizione;
import com.emporio.emporio.services.AcquirenteService;
import com.emporio.emporio.services.DipendenteService;
import com.emporio.emporio.services.OrdineService;
import com.emporio.emporio.services.TitolareService;
import com.emporio.emporio.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
    private OrdineService orderService;

    @Autowired
    private AcquirenteService customerService;

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

    @GetMapping("/users/{username}/orders")
    public ResponseEntity<List<OrdineHistoryDto>> getAllCustomerOrders(@NotBlank @PathVariable(name = "username", required = true) String username) {
        Acquirente customer = this.customerService.getAcquirente(username);
        List<OrdineHistoryDto> orderList = this.orderService.getCustomerOrders(customer)
                                              .stream()
                                              .map((order) -> this.modelMapper.map(order, OrdineHistoryDto.class))
                                              .collect(Collectors.toList());
        return ResponseEntity.ok(orderList);
    }
}