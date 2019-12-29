package com.emporio.emporio.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;
import com.emporio.emporio.model.CategoriaProdotto;
import com.emporio.emporio.repository.CategoriaProdottoRepository;

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
public class CategoriaProdottoController {

    @Autowired
    private CategoriaProdottoRepository categoryProductRepository;

    @PostMapping("/categoryProduct")
    public ResponseEntity<String> insertNewCategoryProduct(@Valid @RequestBody CategoriaProdotto categoryProduct)
            throws URISyntaxException {
        if (categoryProductRepository.existsByDescription(categoryProduct.getDescription())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        categoryProductRepository.save(categoryProduct);
        
        return ResponseEntity.created(new URI("/categoryProduct/"+ categoryProduct.getCategoryId())).build();
    }

    @GetMapping("/categoryProduct")
    public ResponseEntity<List<CategoriaProdotto>> getAllCategories() {
        List<CategoriaProdotto> toReturnCategoryProductList = categoryProductRepository.findAll();

        return ResponseEntity.ok(toReturnCategoryProductList);
    }
}