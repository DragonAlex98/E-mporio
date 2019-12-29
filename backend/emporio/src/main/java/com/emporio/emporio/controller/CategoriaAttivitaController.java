package com.emporio.emporio.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;
import com.emporio.emporio.model.CategoriaAttivita;
import com.emporio.emporio.repository.CategoriaAttivitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CategoriaAttivitaController {

    @Autowired
    private CategoriaAttivitaRepository categoryShopRepository;

    @PostMapping("/categoryShop")
    public ResponseEntity<String> insertNewCategoryShop(@Valid @RequestBody CategoriaAttivita categoryShop)
            throws URISyntaxException {
        if (categoryShopRepository.existsByShopCategoryDescription(categoryShop.getShopCategoryDescription())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        categoryShopRepository.save(categoryShop);
        
        return ResponseEntity.created(new URI("/categoryShop/"+ categoryShop.getShopCategoryId())).build();
    }
    
    @GetMapping("/categoryShop")
    public ResponseEntity<List<CategoriaAttivita>> getAllShops() {
        List<CategoriaAttivita> toReturnCategoryShopList = categoryShopRepository.findAll();

        return ResponseEntity.ok(toReturnCategoryShopList);
    }
}