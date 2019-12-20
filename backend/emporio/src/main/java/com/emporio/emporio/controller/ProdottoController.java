package com.emporio.emporio.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.emporio.emporio.security.WebSecurityConfig;
import com.emporio.emporio.config.ProductDescriptionForm;
import com.emporio.emporio.model.CategoriaProdotto;
import com.emporio.emporio.model.ProdottoDescrizione;
import com.emporio.emporio.repository.CategoriaProdottoRepository;
import com.emporio.emporio.repository.ProdottoDescrizioneRepository;

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
    private ProdottoDescrizioneRepository productRepository;

    @Autowired
    private CategoriaProdottoRepository productCategoryRepository;

    private static final ProdottoController instance = new ProdottoController();

    //private constructor to avoid client applications to use constructor
    private ProdottoController() {
        
    }

    public static ProdottoController getInstance(){
        return instance;
    }

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<String> insertNewProduct(@Valid @RequestBody ProductDescriptionForm product) {
        if (productRepository.existsByProductName(product.getProductName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<CategoriaProdotto> cat = productCategoryRepository.findByDescription(product.getProductCategoryName());
        if (!cat.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ProdottoDescrizione newProduct = ProdottoDescrizione.builder().productName(product.getProductName()).productCategory(cat.get()).build();
        productRepository.save(newProduct);

        String toReturnString = "{"
        +"'id':'"+newProduct.getProductId()+"',"
        +"'url':'"+WebSecurityConfig.appURL + WebSecurityConfig.apiURI + "/products/"+ newProduct.getProductId() + "',"
        +"'type':'product'"
        +"}";
        
        return new ResponseEntity<>(toReturnString, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/products/search", method = RequestMethod.GET)
    public ResponseEntity<List<ProdottoDescrizione>> findProduct(@RequestParam(name = "nome", required = true) String nome) {
        
        if(nome == "")
            return new ResponseEntity<List<ProdottoDescrizione>>(HttpStatus.BAD_REQUEST);

        List<ProdottoDescrizione> toReturnProductsList = productRepository.findByProductNameContaining(nome);

        if(toReturnProductsList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<ProdottoDescrizione>>(toReturnProductsList, HttpStatus.OK);
    }


    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<ProdottoDescrizione>> getAllProducts() {
        List<ProdottoDescrizione> toReturnProductList = productRepository.findAll();

        return new ResponseEntity<List<ProdottoDescrizione>>(toReturnProductList, HttpStatus.OK);
    }

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<ProdottoDescrizione>> getProductById(Integer id) {
        return new ResponseEntity<Optional<ProdottoDescrizione>>(productRepository.findById(id), HttpStatus.OK);
    }
}