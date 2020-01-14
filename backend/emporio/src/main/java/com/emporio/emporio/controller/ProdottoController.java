package com.emporio.emporio.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.emporio.emporio.dto.ProductPostDto;
import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.Prodotto;
import com.emporio.emporio.model.ProdottoDescrizione;
import com.emporio.emporio.services.AttivitaService;
import com.emporio.emporio.services.CatalogoService;
import com.emporio.emporio.services.ProdottoDescrizioneService;
import com.emporio.emporio.services.ProdottoService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * ProdottoController
 */
@RestController
@RequestMapping("/api/v1")
public class ProdottoController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProdottoDescrizioneService productDescriptionService;

    @Autowired
    private ProdottoService productService;

    @Autowired
    private AttivitaService shopService;

    @Autowired
    private CatalogoService catalogoService;

    @PostMapping(value="/shops/{piva}/products")
    public ResponseEntity<String> insertNewProductOnShopCatalog(@AuthenticationPrincipal UserDetails userDetails ,@NotBlank @PathVariable(name = "piva", required = true) String piva ,@RequestBody ProductPostDto productDto) {
        //TODO: process POST request AGGIUNTA DI UN PRODOTTO AL CATALOGO.
        Attivita shop = this.shopService.getShop(piva);

        ProdottoDescrizione productDescription = this.productDescriptionService.getProductDescriptionFrom(productDto.getProductName());

        Prodotto product = Prodotto.builder().productDescription(productDescription).productPrice(productDto.getPrice()).build();

        shop.setCatalog(this.catalogoService.addProductToCatalog(product, shop.getCatalog()));
        
        return ResponseEntity.ok("Prodotto " + productDto.getProductName() + " aggiunto al catalogo!");
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Prodotto>> findProduct(@NotBlank @RequestParam(name = "nome", required = true) String nome) {
        //Ricerca tutti i prodotti contenenti una stringa e associati ad una attività
        return ResponseEntity.ok(productService.getAllProductsContaining(nome));
    }

    @GetMapping("/products")
    public ResponseEntity<List<Prodotto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    
    
}