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