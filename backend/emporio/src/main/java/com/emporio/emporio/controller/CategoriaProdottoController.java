package com.emporio.emporio.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;
import com.emporio.emporio.model.CategoriaProdotto;
import com.emporio.emporio.services.CategoriaProdottoService;
import com.emporio.emporio.util.ApiPostResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaProdottoController {

    @Autowired
    private CategoriaProdottoService categoryProductService;

    @PostMapping("/products/categories")
    public ResponseEntity<ApiPostResponse> insertNewCategoryProduct(@Valid @RequestBody CategoriaProdotto productCategory) {
        CategoriaProdotto categoryProduct = categoryProductService.insertNewCategoryProduct(productCategory.getDescription());
        return ResponseEntity.ok(ApiPostResponse.builder().message("Categoria " + categoryProduct.getDescription() + " aggiunta!").build());
    }

    @GetMapping("/products/categories")
    public ResponseEntity<List<CategoriaProdotto>> getAllCategories() {
        return ResponseEntity.ok(categoryProductService.getAllProductCategories());
    }
}