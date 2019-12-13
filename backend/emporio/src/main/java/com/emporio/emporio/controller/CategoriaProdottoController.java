package com.emporio.emporio.controller;

import java.util.List;

import javax.validation.Valid;
import com.emporio.emporio.security.WebSecurityConfig;
import com.emporio.emporio.model.CategoriaProdotto;
import com.emporio.emporio.repository.CategoriaProdottoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CategoriaProdottoController {

    @Autowired
    private CategoriaProdottoRepository categoryProductRepository;

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/categoryProduct", method = RequestMethod.POST)
    public ResponseEntity<String> insertNewCategoryProduct(@Valid @RequestBody CategoriaProdotto categoryProduct) {
        if (categoryProductRepository.existsByDescription(categoryProduct.getDescription()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        categoryProductRepository.save(categoryProduct);
        String toReturnString = "{"
        +"'id':'"+categoryProduct.getCategoryId()+"',"
        +"'url':'"+WebSecurityConfig.appURL + WebSecurityConfig.apiURI + "/categoryProduct/"+ categoryProduct.getCategoryId() + "',"
        +"'type':'categoryProduct'"
        +"}";
        
        return new ResponseEntity<>(toReturnString, HttpStatus.CREATED);
    }

    
    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/categoryProduct", method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaProdotto>> getAllCategories() {
        List<CategoriaProdotto> toReturnCategoryProductList = categoryProductRepository.findAll();

        return new ResponseEntity<List<CategoriaProdotto>>(toReturnCategoryProductList, HttpStatus.OK);
    }
}