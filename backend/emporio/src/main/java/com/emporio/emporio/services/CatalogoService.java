package com.emporio.emporio.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.Catalogo;
import com.emporio.emporio.model.Prodotto;
import com.emporio.emporio.repository.CatalogoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CatalogoService
 */
@Service
public class CatalogoService {

    @Autowired
    private CatalogoRepository catalogoRepo;

    public Catalogo addProductToCatalog(Prodotto product, Catalogo catalogo) {
        catalogo.getProducts().add(product);
        return this.catalogoRepo.save(catalogo);
    }

    public void checkProductAlreadyPresentInCatalog(Catalogo catalogo, String productName) {
        if(catalogo.containsProduct(productName))
            throw new EntityExistsException("Il prodotto " + productName + " è già presente nell'attività!");
    }
}