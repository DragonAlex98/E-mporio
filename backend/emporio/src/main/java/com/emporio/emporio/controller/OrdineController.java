package com.emporio.emporio.controller;

import javax.validation.Valid;

import com.emporio.emporio.model.Ordine;
import com.emporio.emporio.repository.OrdineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


//TODO Da terminare, verificare prima come rappresentare la lista di podotti nell'ordine
@RestController
@RequestMapping("/api/v1")
public class OrdineController {
    @Autowired
    private OrdineRepository orderRepository;

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity<String> createNewOrder(@Valid @RequestBody Ordine newOrdine) {
        /* TODO da implementare
        ProdottoController controllerProd = ProdottoController.getInstance();

        for(OrderDetails orderDetail : newOrdine.getOrderDetails()) {
            orderDetail.setProdotto(controllerProd.getProductById(orderDetail.getProdotto().getProductId()).getBody().get());
            orderDetail.setOrdine(newOrdine);
        } */

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}