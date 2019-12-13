package com.emporio.emporio.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.emporio.emporio.security.WebSecurityConfig;
import com.emporio.emporio.model.Prodotto;
import com.emporio.emporio.repository.ProdottoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProdottoController {
    @Autowired
    private ProdottoRepository productRepository;

    private static final ProdottoController instance = new ProdottoController();

    //private constructor to avoid client applications to use constructor
    private ProdottoController() {
        
    }

    public static ProdottoController getInstance(){
        return instance;
    }

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<String> insertNewProduct(@Valid @RequestBody Prodotto product) {
        if (productRepository.existsByProductName(product.getProductName()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        productRepository.save(product);
        String toReturnString = "{"
        +"'id':'"+product.getProductId()+"',"
        +"'url':'"+WebSecurityConfig.appURL + WebSecurityConfig.apiURI + "/products/"+ product.getProductId() + "',"
        +"'type':'product'"
        +"}";
        
        return new ResponseEntity<>(toReturnString, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/products/search", method = RequestMethod.GET)
    public ResponseEntity<List<Prodotto>> findProduct(@RequestParam(name = "nome", required = true) String nome) {
        
        if(nome == "")
            return new ResponseEntity<List<Prodotto>>(HttpStatus.BAD_REQUEST);

        List<Prodotto> toReturnProductsList = productRepository.findByProductNameContaining(nome);

        if(toReturnProductsList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<Prodotto>>(toReturnProductsList, HttpStatus.OK);
    }


    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<Prodotto>> getAllProducts() {
        List<Prodotto> toReturnProductList = productRepository.findAll();

        return new ResponseEntity<List<Prodotto>>(toReturnProductList, HttpStatus.OK);
    }

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Prodotto>> getProductById(Integer id) {
        return new ResponseEntity<Optional<Prodotto>>(productRepository.findById(id), HttpStatus.OK);
    }
}