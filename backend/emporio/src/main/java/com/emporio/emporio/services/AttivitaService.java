package com.emporio.emporio.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.Catalogo;
import com.emporio.emporio.model.Prodotto;
import com.emporio.emporio.repository.AttivitaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AttivitaService
 */
@Service
public class AttivitaService {

    @Autowired
    private AttivitaRepository shopRepository;

    public boolean existsAttivita(String pIva) {
        return shopRepository.existsAttivitaByShopDescription_shopPIVA(pIva);
    }

    public boolean existsAttivita(Attivita pIva) {
        return shopRepository.existsAttivitaByShopDescription_shopPIVA(pIva.getShopDescription().getShopPIVA());
    }

    public Attivita addShop(Attivita shop) {
        if(this.existsAttivita(shop.getShopDescription().getShopPIVA()))
            throw new EntityExistsException("L'attività con partita iva " + shop.getShopDescription().getShopPIVA() + " è già registrata nel sistema!");
            
        return shopRepository.save(shop);
    }

    public List<Attivita> getShopsContaining(String businessName) {
        List<Attivita> toReturnShopsList = shopRepository.findByShopDescription_shopBusinessNameContaining(businessName);

        if(toReturnShopsList.isEmpty())
            throw new EntityNotFoundException("Non sono presenti attività con ragione sociale " + businessName);

        return toReturnShopsList;
    }

    public Attivita getShop(String pIva) {
        Optional<Attivita> shop = shopRepository.findByShopDescription_shopPIVA(pIva);

        if(!shop.isPresent())
            throw new EntityNotFoundException("L'attività con partita iva " + pIva + "non è registrata nel sistema!");

        return shop.get();
    }

    public void deleteAttivitaBy(String pIva) {
        shopRepository.deleteByShopDescription_shopPIVA(pIva);
    }

    public void deleteAttivita(Attivita shop) {
        shopRepository.delete(shop);
    }

    public Set<Prodotto> getCatalogProducts(String pIva) {
        Attivita shop = this.getShop(pIva);
        return shop.getCatalog().getProducts();
    }

    public boolean deleteCatalogProduct(Attivita shop, Prodotto product) {

        if(!shop.getCatalog().getProducts().remove(product)) {
            throw new EntityNotFoundException("Il catalogo dell'attività con partita iva " + shop.getShopDescription().getShopPIVA() + " non contiene il prodotto col nome " + product.getProductDescription().getProductName());
        }

        shopRepository.save(shop);

        return true;
    }

    public Prodotto getProductFromCatalog(Attivita shop, String productName) {
        Catalogo catalogo = shop.getCatalog();

        Optional<Prodotto> product = catalogo.getProducts().stream()
                                                        .filter(prod -> prod.getProductDescription().getProductName().equals(productName))
                                                        .findFirst();

        if(!product.isPresent())
            throw new EntityNotFoundException("Il prodotto non è contenuto nel catalogo " + productName);

        return product.get();
    }
}