package com.emporio.emporio.controller;

import java.util.List;

import javax.validation.Valid;
import com.emporio.emporio.security.WebSecurityConfig;
import com.emporio.emporio.model.CategoriaAttivita;
import com.emporio.emporio.repository.CategoriaAttivitaRepository;
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
public class CategoriaAttivitaController {

    @Autowired
    private CategoriaAttivitaRepository categoryShopRepository;

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/categoryShop", method = RequestMethod.POST)
    public ResponseEntity<String> insertNewCategoryShop(@Valid @RequestBody CategoriaAttivita categoryShop) {
        if (categoryShopRepository.existsByShopCategoryDescription(categoryShop.getShopCategoryDescription()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        categoryShopRepository.save(categoryShop);
        String toReturnString = "{"
        +"'id':'"+categoryShop.getShopCategoryId()+"',"
        +"'url':'"+WebSecurityConfig.appURL + WebSecurityConfig.apiURI + "/categoryShop/"+ categoryShop.getShopCategoryId() + "',"
        +"'type':'categoryShop'"
        +"}";
        
        return new ResponseEntity<>(toReturnString, HttpStatus.CREATED);
    }

    
    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/categoryShop", method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaAttivita>> getAllShops() {
        List<CategoriaAttivita> toReturnCategoryShopList = categoryShopRepository.findAll();

        return new ResponseEntity<List<CategoriaAttivita>>(toReturnCategoryShopList, HttpStatus.OK);
    }
}