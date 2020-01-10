package com.emporio.emporio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.CategoriaAttivita;
import com.emporio.emporio.repository.CategoriaAttivitaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CategoriaAttivitaService
 */
@Service
public class CategoriaAttivitaService {
    @Autowired
    private CategoriaAttivitaRepository shopCategoryRepository;

    public boolean existsShopCategoryDescription(String shopCategoryDescription) {
        return shopCategoryRepository.existsByShopCategoryDescription(shopCategoryDescription);
    }

    public CategoriaAttivita getShopCategory(String shopCategory) {
        Optional<CategoriaAttivita> categoria = shopCategoryRepository.findByShopCategoryDescription(shopCategory);
        if (!categoria.isPresent()) {
            throw new EntityNotFoundException("Categoria " + shopCategory + " non trovata!");
        }

        return categoria.get();
    }

    public List<CategoriaAttivita> getAllShopCategories() {
        return shopCategoryRepository.findAll();
    }

    public CategoriaAttivita insertNewCategoryShop(String shopCategoryDescription) {
        if (existsShopCategoryDescription(shopCategoryDescription)) {
            throw new EntityExistsException("La categoria " + shopCategoryDescription + " è già stata inserita nel sistema");
        }

        return shopCategoryRepository.save(CategoriaAttivita.builder().shopCategoryDescription(shopCategoryDescription).build());
    }
    
}