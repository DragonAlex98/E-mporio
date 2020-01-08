package com.emporio.emporio.services;

import java.util.Optional;

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

    public CategoriaAttivita getShopCategory(String shopCategory) {
        Optional<CategoriaAttivita> categoria = shopCategoryRepository.findByShopCategoryDescription(shopCategory);
        if (!categoria.isPresent()) {
            throw new EntityNotFoundException("Categoria " + shopCategory + " non trovata!");
        }

        return categoria.get();
    }
    
}