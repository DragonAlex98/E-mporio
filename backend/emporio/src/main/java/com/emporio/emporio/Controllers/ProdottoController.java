package com.emporio.emporio.Controllers;

import java.util.List;

import javax.validation.Valid;

import com.emporio.emporio.Config.AppConfig;
import com.emporio.emporio.Models.Prodotto;
import com.emporio.emporio.Repositories.ProdottoRepository;

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

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<String> insertNewProduct(@Valid @RequestBody Prodotto product) {
        if (productRepository.existsByProductName(product.getProductName()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        productRepository.save(product);
        String toReturnString = "{"
        +"'id':'"+product.getProductId()+"',"
        +"'url':'"+AppConfig.appURL + AppConfig.apiURI + "/products/"+ product.getProductId() + "',"
        +"'type':'product'"
        +"}";
        
        return new ResponseEntity<>(toReturnString, HttpStatus.CREATED);
    }

    //TODO Da sistemare, non funziona piu' in seguito all'inserimento della categoria
    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/products/search", method = RequestMethod.GET)
    public ResponseEntity<List<Prodotto>> findProduct(@RequestParam(name = "nome", required = true) String nome,
                                        @RequestParam(name = "prezzo", required = false, defaultValue = "0") Double prezzo) {
        
        if(nome == "")
            return new ResponseEntity<List<Prodotto>>(HttpStatus.BAD_REQUEST);

        List<Prodotto> toReturnProductsList = productRepository.findProduct(nome, prezzo);

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
}