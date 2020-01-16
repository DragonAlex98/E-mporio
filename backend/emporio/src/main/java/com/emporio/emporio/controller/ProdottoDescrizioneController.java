package com.emporio.emporio.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import com.emporio.emporio.dto.ProductDescriptionDto;
import com.emporio.emporio.model.CategoriaProdotto;
import com.emporio.emporio.model.ProdottoDescrizione;
import com.emporio.emporio.services.CategoriaProdottoService;
import com.emporio.emporio.services.ProdottoDescrizioneService;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdottoDescrizioneController {

    @Autowired
    private CategoriaProdottoService productCategoryService;

    @Autowired
    private ProdottoDescrizioneService  productDescriptionService;

    @PostMapping("/products")
    public ResponseEntity<String> insertNewProduct(@Valid @RequestBody ProductDescriptionDto product)
            throws URISyntaxException {

        CategoriaProdotto cat = productCategoryService.getProductCategory(product.getProductCategoryName());

        ProdottoDescrizione newProduct = ProdottoDescrizione.builder().productName(product.getProductName()).productCategory(cat).build();
        
        newProduct = productDescriptionService.saveProductDescription(newProduct);
        
        return ResponseEntity.created(new URI("/products/" + newProduct.getProductId())).body("Prodotto " + newProduct.getProductName() + " aggiunto!");
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProdottoDescrizione>> getAllProductDescriptions() {
        return ResponseEntity.ok(productDescriptionService.getAllProductsDescription());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProdottoDescrizione> getProductById(@Valid @Type(value = Integer.class) @PathVariable(name = "id", required = true) Integer id) {
        //TODO VALUTARE SE TENERE
        return ResponseEntity.ok(productDescriptionService.getProductDescriptionById(id));
    }
}