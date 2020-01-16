package com.emporio.emporio.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;
import com.emporio.emporio.model.CategoriaAttivita;
import com.emporio.emporio.services.CategoriaAttivitaService;
import com.emporio.emporio.util.ApiPostResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaAttivitaController {

    @Autowired
    private CategoriaAttivitaService categoriaAttivitaService;

    @PostMapping("/shops/categories")
    public ResponseEntity<ApiPostResponse> insertNewCategoryShop(@Valid @RequestBody CategoriaAttivita categoryShop) throws URISyntaxException {
        CategoriaAttivita shopCategory = categoriaAttivitaService.insertNewCategoryShop(categoryShop.getShopCategoryDescription());
        return ResponseEntity.created(new URI("/categoryShop/"+ shopCategory.getShopCategoryId())).body(ApiPostResponse.builder().message("Categoria aggiunta!").build());
    }
    
    @GetMapping("/shops/categories")
    public ResponseEntity<List<CategoriaAttivita>> getAllShopCategories() {
        return ResponseEntity.ok(categoriaAttivitaService.getAllShopCategories());
    }
}