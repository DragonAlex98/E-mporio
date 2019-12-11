package com.emporio.emporio.Controllers;

import javax.validation.Valid;

import com.emporio.emporio.Models.Prodotto;
import com.emporio.emporio.Repositories.OrdineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    public ResponseEntity<String> createNewOrder() {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}