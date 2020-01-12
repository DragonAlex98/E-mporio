package com.emporio.emporio.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.emporio.emporio.dto.OrdineDto;
import com.emporio.emporio.dto.OrdineGetDto;
import com.emporio.emporio.model.Acquirente;
import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.Ordine;
import com.emporio.emporio.services.AcquirenteService;
import com.emporio.emporio.services.DipendenteService;
import com.emporio.emporio.services.OrdineService;
import com.emporio.emporio.services.RigaOrdineProdottoService;
import com.emporio.emporio.services.TitolareService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/v1")
public class OrdineController {

    @Autowired
    private OrdineService orderService;

    @Autowired
    private TitolareService ownerService;
    
    @Autowired
    private DipendenteService employeeService;

    @Autowired
    private RigaOrdineProdottoService orderProductLineService;

    @Autowired
    private AcquirenteService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity<String> createNewOrder(@Valid @RequestBody OrdineDto newOrdine) {
        String worker = newOrdine.getEmployeeUsername();

        boolean isDipendente = employeeService.existsDipendente(worker);
        boolean isTitolare = ownerService.existsTitolare(worker);

        if (!isDipendente && !isTitolare) {
            return ResponseEntity.badRequest().body("Utente non trovato o permessi non adeguati");
        }

        Attivita shop;
        if (isTitolare) {
            shop = ownerService.getShopOwnedBy(worker);
        } else {
            shop = employeeService.getShopEmployedIn(worker);
        }

        newOrdine.setProductsList(orderProductLineService.checkLines(shop, newOrdine.getProductsList()));

        Acquirente customer = customerService.getAcquirente(newOrdine.getCustomerUsername());

        Ordine order = Ordine.builder()
                            .orderCustomer(customer)
                            .orderShop(shop)
                            .parkingAddress(newOrdine.getCarPosition())
                            .orderProductsLineList(newOrdine.getProductsList())
                            .build();

        order = orderService.saveOrdine(order);

        order.setOrderProductsLineList(orderProductLineService.saveAllLines(order, newOrdine.getProductsList()));

        return ResponseEntity.created(URI.create("/orders/" + order.getOrderId())).build();
    }

    @GetMapping(value="/orders/state/not-assigned")
    public ResponseEntity<List<OrdineGetDto>> getAllNotAssignedOrders() {
        List<OrdineGetDto> ordersList = orderService.getAllNotAssignedOrders()
                                                    .stream()
                                                    .map(this::convertToDto)
                                                    .collect(Collectors.toList());

        return ResponseEntity.ok(ordersList);
    }

    private OrdineGetDto convertToDto(Ordine order) {
        OrdineGetDto orderDto = this.modelMapper.map(order, OrdineGetDto.class);
        return orderDto;
    }
}