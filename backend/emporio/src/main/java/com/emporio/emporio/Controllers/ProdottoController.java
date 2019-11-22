package com.emporio.emporio.Controllers;

import java.util.List;

import javax.validation.Valid;

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
        productRepository.save(product);
        String toReturnString = "{"
        +"'id':'"+product.getProductId()+"',"
        +"'url':'http://localhost:8000/api/v1/products/"+ product.getProductId() + "',"
        +"'type':'product'"
        +"}";
        
        return new ResponseEntity<>(toReturnString, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/products/search", method = RequestMethod.GET)
    public ResponseEntity<List<Prodotto>> findProduct(@RequestParam(name = "id", required = false, defaultValue = "0") Integer id,
                                        @RequestParam(name = "nome", required = true) String nome,
                                        @RequestParam(name = "prezzo", required = false, defaultValue = "0") Double prezzo,
                                        @RequestParam(name = "categoria", required = false, defaultValue = "0") Integer categoria) {
        
        List<Prodotto> toReturnProductsList = productRepository.findProduct(id, nome, prezzo, categoria);
        return new ResponseEntity(toReturnProductsList, HttpStatus.OK);
    }
}