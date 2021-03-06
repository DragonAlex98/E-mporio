package com.emporio.emporio.controller;

import java.net.URI;
import java.util.List;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.emporio.emporio.dto.OrdineDto;
import com.emporio.emporio.dto.OrdineGetDto;
import com.emporio.emporio.dto.OrdineHistoryDto;
import com.emporio.emporio.model.Acquirente;
import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.Ordine;
import com.emporio.emporio.model.Prodotto;
import com.emporio.emporio.model.RigaOrdineProdotto;
import com.emporio.emporio.services.AcquirenteService;
import com.emporio.emporio.services.AttivitaService;
import com.emporio.emporio.services.ConsegnaService;
import com.emporio.emporio.services.DipendenteService;
import com.emporio.emporio.services.OrdineService;
import com.emporio.emporio.services.PostoService;
import com.emporio.emporio.services.RigaOrdineProdottoService;
import com.emporio.emporio.services.TitolareService;
import com.emporio.emporio.util.ApiPostResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
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
    private AttivitaService shopService;

    @Autowired
    private AcquirenteService customerService;

    @Autowired
    private ConsegnaService deliveryService;

    @Autowired
    private PostoService postoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/orders")
    public ResponseEntity<ApiPostResponse> createNewOrder(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody OrdineDto orderDto) {
        String worker = userDetails.getUsername();

        boolean isDipendente = employeeService.existsDipendente(worker);
        boolean isTitolare = ownerService.existsTitolare(worker);

        if (!isDipendente && !isTitolare) {
            return ResponseEntity.badRequest().body(ApiPostResponse.builder().message("Utente non trovato o permessi non adeguati").build());
        }

        Attivita shop;
        if (isTitolare) {
            shop = ownerService.getShopOwnedBy(worker);
        } else {
            shop = employeeService.getShopEmployedIn(worker);
        }
        List<RigaOrdineProdotto> lines = new ArrayList<RigaOrdineProdotto>();

        // CONTROLLO RIGHE
        for(Entry<String, Integer> line : orderDto.getLines().entrySet()) {
            Prodotto product = this.shopService.getProductFromCatalog(shop, line.getKey());
            lines.add(RigaOrdineProdotto.builder().product(product.getProductDescription()).quantity(line.getValue()).build());
        }

        Acquirente customer = customerService.getAcquirente(orderDto.getCustomerUsername());

        Ordine order = Ordine.builder()
                            .orderCustomer(customer)
                            .orderShop(shop.getShopDescription())
                            .parkingAddress(orderDto.getCarPosition())
                            .build();

        order = orderService.saveOrdine(order);

        order.setOrderProductsLineList(orderProductLineService.saveAllLines(order, lines));

        return ResponseEntity.created(URI.create("/orders/" + order.getOrderId())).body(ApiPostResponse.builder().message("Aggiunto ordine").build());
    }

    @GetMapping("/orders/state/not-assigned")
    public ResponseEntity<List<OrdineGetDto>> getAllNotAssignedOrders() {
        List<OrdineGetDto> ordersList = orderService.getAllNotAssignedOrders()
                                                    .stream()
                                                    .map(this::convertToDto)
                                                    .collect(Collectors.toList());

        return ResponseEntity.ok(ordersList);
    }

    @PutMapping("/orders/{id}/delivery/states/ritirata")
    public ResponseEntity<OrdineHistoryDto> pickUpGoods(@PathVariable(name = "id", required = true) Long orderId) {
        Ordine order =  this.orderService.getOrdine(orderId);
        order.setOrderConsegna(this.deliveryService.changeDeliveryStatus(order.getOrderConsegna(), 2));
        this.postoService.detachConsegnaFrom(order.getOrderConsegna().getPosto());
        order.getOrderConsegna().setPosto(null);
        return ResponseEntity.ok(this.modelMapper.map(order, OrdineHistoryDto.class));
    }

    private OrdineGetDto convertToDto(Ordine order) {
        OrdineGetDto orderDto = this.modelMapper.map(order, OrdineGetDto.class);
        return orderDto;
    }
}