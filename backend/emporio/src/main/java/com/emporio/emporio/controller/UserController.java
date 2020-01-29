package com.emporio.emporio.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.emporio.emporio.dto.AttivitaDescrizioneGetDto;
import com.emporio.emporio.dto.AuthenticationRequest;
import com.emporio.emporio.dto.OrdineHistoryDto;
import com.emporio.emporio.dto.UtenteGetDto;
import com.emporio.emporio.factory.AdminUserFactory;
import com.emporio.emporio.factory.OperatoreSistemaUserFactory;
import com.emporio.emporio.model.Acquirente;
import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.AttivitaDescrizione;
import com.emporio.emporio.model.User;
import com.emporio.emporio.services.AcquirenteService;
import com.emporio.emporio.services.DipendenteService;
import com.emporio.emporio.services.GestoreMarketingService;
import com.emporio.emporio.services.OrdineService;
import com.emporio.emporio.services.RoleService;
import com.emporio.emporio.services.TitolareService;
import com.emporio.emporio.services.UserService;
import com.emporio.emporio.util.ApiPostResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DipendenteService employeeService;

    @Autowired
    private TitolareService titolareService;

    @Autowired
    private OrdineService orderService;

    @Autowired
    private AcquirenteService customerService;

    @Autowired
    private GestoreMarketingService gestoreMarketingService;

    @GetMapping("/users/{username}")
    public ResponseEntity<Boolean> existsUser(@NotBlank @PathVariable(name = "username", required = true) String username) {
        boolean exists = userService.existsUser(username);
        return (exists) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAuthority('TOGGLE_USER')")
    @PutMapping("/users/{username}")
    public ResponseEntity<ApiPostResponse> enableDisableUser(@NotBlank @PathVariable(name = "username", required = true) String username) {
        User user = userService.getUser(username);
        boolean enabled = user.getEnabled();
        user.setEnabled(!enabled);
        userService.updateUser(user);
        return ResponseEntity.ok(ApiPostResponse.builder().message("L'utente " + username + " è stato " + ((enabled) ? "disabilitato" : "abilitato")).build());
    }

    @PreAuthorize("hasAuthority('CHECK_USER')")
    @GetMapping("/users/{username}/state")
    public ResponseEntity<Boolean> getUserState(@NotBlank @PathVariable(name = "username", required = true) String username) {
        User user = userService.getUser(username);
        return ResponseEntity.ok(user.getEnabled());
    }

    @PreAuthorize("hasAuthority('CREATE_USER')")
    @PostMapping("/users/admins")
    public ResponseEntity<ApiPostResponse> createAdmin(@Valid @RequestBody AuthenticationRequest data) {
        User newAdmin = new AdminUserFactory().createUser(data.getUsername(), data.getPassword(), this.roleService::getRole);
        userService.createUser(newAdmin);
        return ResponseEntity.ok(ApiPostResponse.builder().message("L'admin " + newAdmin.getUsername() + " è stato creato!").build());
    }

    @PreAuthorize("hasAuthority('CREATE_USER')")
    @PostMapping("/users/operators")
    public ResponseEntity<ApiPostResponse> createOperator(@Valid @RequestBody AuthenticationRequest data) {
        User newOperatore = new OperatoreSistemaUserFactory().createUser(data.getUsername(), data.getPassword(), this.roleService::getRole);
        userService.createUser(newOperatore);
        return ResponseEntity.ok(ApiPostResponse.builder().message("L'operatore " + newOperatore.getUsername() + " è stato creato!").build());
    }

    @GetMapping("/users/{username}/shops")
    public ResponseEntity<AttivitaDescrizioneGetDto> getShop(@NotBlank @PathVariable(name = "username", required = true) String username) {
        boolean isDipendente = employeeService.existsDipendente(username);
        boolean isTitolare = titolareService.existsTitolare(username);
        boolean isGestoreMarketing = gestoreMarketingService.existsGestoreMarketing(username);

        Attivita shop;
        if (isTitolare) {
            shop = titolareService.getShopOwnedBy(username);
        } else if (isDipendente) {
            shop = employeeService.getShopEmployedIn(username);
        } else if (isGestoreMarketing) {
            shop = gestoreMarketingService.getShopWorksFor(username);
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

    @PreAuthorize("hasAuthority('CHECK_USER')")
    @GetMapping("/users/search")
    public ResponseEntity<List<UtenteGetDto>> getAllUsersContaining(@AuthenticationPrincipal UserDetails userDetails, @NotBlank @RequestParam(name = "username", required = true) String username) {
        List<UtenteGetDto> users = this.userService.getUsers(username).stream().map(u -> convertUserToDto(u)).collect(Collectors.toList());
        users.removeIf(u -> u.getUsername().equalsIgnoreCase(userDetails.getUsername()));
        return ResponseEntity.ok(users);
    }

    private UtenteGetDto convertUserToDto(User user) {
        return this.modelMapper.map(user, UtenteGetDto.class);
    }
}