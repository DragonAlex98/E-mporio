package com.emporio.emporio.controller;

import java.util.List;

import com.emporio.emporio.model.Prodotto;
import com.emporio.emporio.services.ProdottoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * ProdottoController
 */
@RestController
public class ProdottoController {

    @Autowired
    private ProdottoService productService;

    @GetMapping("/shops/products")
    public ResponseEntity<List<Prodotto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}