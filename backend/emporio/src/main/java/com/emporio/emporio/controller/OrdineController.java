package com.emporio.emporio.controller;

import javax.validation.Valid;

import com.emporio.emporio.dto.NuovoOrdineDto;
import com.emporio.emporio.repository.OrdineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//TODO Da terminare, verificare prima come rappresentare la lista di podotti nell'ordine
@RestController
@RequestMapping("/api/v1")
public class OrdineController {

    @Autowired
    private OrdineRepository orderRepository;

    @PostMapping("/orders")
    public ResponseEntity<String> createNewOrder(@Valid @RequestBody NuovoOrdineDto newOrdine) {
        /* TODO da implementare
        ProdottoController controllerProd = ProdottoController.getInstance();

        for(OrderDetails orderDetail : newOrdine.getOrderDetails()) {
            orderDetail.setProdotto(controllerProd.getProductById(orderDetail.getProdotto().getProductId()).getBody().get());
            orderDetail.setOrdine(newOrdine);
        } */

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}