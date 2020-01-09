package com.emporio.emporio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.ProdottoDescrizione;
import com.emporio.emporio.repository.ProdottoDescrizioneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProdottoDescrizioneService
 */
@Service
public class ProdottoDescrizioneService {

    @Autowired
    private ProdottoDescrizioneRepository productRepository;

    public boolean existsByProductName(String productName) {
        return productRepository.existsByProductName(productName);
    }

    public ProdottoDescrizione getProduct(String productName) {
        Optional<ProdottoDescrizione> product = productRepository.findByProductName(productName);

        if(!product.isPresent()) {
            throw new EntityNotFoundException("Il prodotto con nome " + productName + " non è presente!");
        }

        return product.get();
    }

    public ProdottoDescrizione saveProductDescription(ProdottoDescrizione product) {
        if(productRepository.existsByProductName(product.getProductName()))
            throw new EntityExistsException("Il prodotto " + product.getProductName() + " è già esistente!");

        return productRepository.save(product);        
    }

    public List<ProdottoDescrizione> getProductsContaining(String name) {
        List<ProdottoDescrizione> toReturnProductsList = productRepository.findByProductNameContaining(name);

        if(toReturnProductsList.isEmpty())
            throw new EntityNotFoundException("Non è stato trovato alcun prodotto con nome " + name);

        return toReturnProductsList;
    }

    public List<ProdottoDescrizione> getAllProductsDescription() {
        List<ProdottoDescrizione> toReturnProductsList = productRepository.findAll();

        if(toReturnProductsList.isEmpty())
            throw new EntityNotFoundException("Non è stato trovato alcun prodotto");

        return toReturnProductsList;
    }

    public ProdottoDescrizione getProductDescriptionById(Integer id) {
        Optional<ProdottoDescrizione> product = productRepository.findById(id);

        if(!product.isPresent())
        throw new EntityNotFoundException("Il prodotto con id " + id + " non è presente!");

        return product.get();
    }
}