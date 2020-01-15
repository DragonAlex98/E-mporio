package com.emporio.emporio.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.emporio.emporio.model.Prodotto;
import com.emporio.emporio.services.ProdottoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * ProdottoController
 */
@RestController
@RequestMapping("/api/v1")
public class ProdottoController {

    @Autowired
    private ProdottoService productService;

    @GetMapping("/products/search")
    public ResponseEntity<List<Prodotto>> findProduct(@NotBlank @RequestParam(name = "nome", required = true) String nome) {
        //Ricerca tutti i prodotti contenenti una stringa e associati ad una attività
        return ResponseEntity.ok(productService.getAllProductsContaining(nome));
    }

    @GetMapping("/shops/products")
    public ResponseEntity<List<Prodotto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    
    
}