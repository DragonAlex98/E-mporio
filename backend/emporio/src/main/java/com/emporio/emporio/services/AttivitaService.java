package com.emporio.emporio.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.ProdottoDescrizione;
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
        return shopRepository.existsAttivitaByShopPIVA(pIva);
    }

    public boolean existsAttivita(Attivita pIva) {
        return shopRepository.existsAttivitaByShopPIVA(pIva.getShopPIVA());
    }

    public Attivita addShop(Attivita shop) {
        if(existsAttivita(shop.getShopPIVA()))
            throw new EntityExistsException("L'attività con partita iva " + shop.getShopPIVA() + " è già registrata nel sistema!");
            
        return shopRepository.save(shop);
    }

    public List<Attivita> getShopsContaining(String businessName) {
        List<Attivita> toReturnShopsList = shopRepository.findByShopBusinessNameContaining(businessName);

        if(toReturnShopsList.isEmpty())
            throw new EntityNotFoundException("Non sono presenti attività con ragione sociale " + businessName);

        return toReturnShopsList;
    }

    public Attivita getShop(String pIva) {
        Optional<Attivita> shop = shopRepository.findByShopPIVA(pIva);

        if(!shop.isPresent())
            throw new EntityNotFoundException("L'attività con partita iva " + pIva + "non è registrata nel sistema!");

        return shop.get();
    }

    public void deleteAttivitaBy(String pIva) {
        shopRepository.deleteByShopPIVA(pIva);
    }

    public Set<ProdottoDescrizione> getCatalogProducts(String pIva) {
        Attivita shop = this.getShop(pIva);
        return shop.getCatalog().getProducts();
    }

    public boolean deleteCatalogProduct(Attivita shop, ProdottoDescrizione product) {

        if(!shop.getCatalog().getProducts().remove(product)) {
            throw new EntityNotFoundException("Il catalogo dell'attività con partita iva " + shop.getShopPIVA() + " non contiene il prodotto col nome " + product.getProductName());
        }

        shopRepository.save(shop);

        return true;
    }
}