package com.emporio.emporio.Controllers;

import java.util.List;

import javax.validation.Valid;
import com.emporio.emporio.Config.AppConfig;
import com.emporio.emporio.Models.CategoriaProdotto;
import com.emporio.emporio.Repositories.CategoryProductRepository;

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
    private CategoryProductRepository categoryProductRepository;

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/categoryProduct", method = RequestMethod.POST)
    public ResponseEntity<String> insertNewCategoryProduct(@Valid @RequestBody CategoriaProdotto categoryProduct) {
        if (categoryProductRepository.existsByDescription(categoryProduct.getDescription()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        categoryProductRepository.save(categoryProduct);
        String toReturnString = "{"
        +"'id':'"+categoryProduct.getProductId()+"',"
        +"'url':'"+AppConfig.appURL + AppConfig.apiURI + "/categoryProduct/"+ categoryProduct.getProductId() + "',"
        +"'type':'categoryProduct'"
        +"}";
        
        return new ResponseEntity<>(toReturnString, HttpStatus.CREATED);
    }

    //TODO Non funziona, ricontrollare 
    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/categoryProduct", method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaProdotto>> getAllCategories() {
        List<CategoriaProdotto> toReturnCategoryProductList = categoryProductRepository.findAll();

        return new ResponseEntity<List<CategoriaProdotto>>(toReturnCategoryProductList, HttpStatus.OK);
    }
}