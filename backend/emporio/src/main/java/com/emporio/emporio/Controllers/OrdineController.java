package com.emporio.emporio.Controllers;

import javax.validation.Valid;

import com.emporio.emporio.Models.Ordine;
import com.emporio.emporio.Repositories.OrdineRepository;
import com.emporio.emporio.Utils.OrderDetails;

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