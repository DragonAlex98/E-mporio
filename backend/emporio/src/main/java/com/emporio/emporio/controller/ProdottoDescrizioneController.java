package com.emporio.emporio.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.emporio.emporio.dto.ProductDescriptionDto;
import com.emporio.emporio.model.CategoriaProdotto;
import com.emporio.emporio.model.ProdottoDescrizione;
import com.emporio.emporio.repository.CategoriaProdottoRepository;
import com.emporio.emporio.repository.ProdottoDescrizioneRepository;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProdottoDescrizioneController {
    
    @Autowired
    private ProdottoDescrizioneRepository productRepository;

    @Autowired
    private CategoriaProdottoRepository productCategoryRepository;

    @PostMapping("/products")
    public ResponseEntity<String> insertNewProduct(@Valid @RequestBody ProductDescriptionDto product)
            throws URISyntaxException {
        if (productRepository.existsByProductName(product.getProductName())) {
            return ResponseEntity.badRequest().build();
        }

        Optional<CategoriaProdotto> cat = productCategoryRepository.findByDescription(product.getProductCategoryName());
        if (!cat.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        ProdottoDescrizione newProduct = ProdottoDescrizione.builder().productName(product.getProductName()).productCategory(cat.get()).build();
        productRepository.save(newProduct);
        
        return ResponseEntity.created(new URI("/products/" + newProduct.getProductId())).build();
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<ProdottoDescrizione>> findProduct(@NotBlank @RequestParam(name = "nome", required = true) String nome) {
        
        List<ProdottoDescrizione> toReturnProductsList = productRepository.findByProductNameContaining(nome);

        if(toReturnProductsList.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(toReturnProductsList);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProdottoDescrizione>> getAllProducts() {
        List<ProdottoDescrizione> toReturnProductsList = productRepository.findAll();

        return ResponseEntity.ok(toReturnProductsList);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Optional<ProdottoDescrizione>> getProductById(@Valid @Type(value = Integer.class) @PathVariable(name = "id", required = true) Integer id) {
        Optional<ProdottoDescrizione> product = productRepository.findById(id);

        return (product.isPresent()) ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }
}