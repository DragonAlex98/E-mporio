package com.emporio.emporio.services;

import java.util.List;

import javax.persistence.EntityExistsException;

import com.emporio.emporio.model.CategoriaProdotto;
import com.emporio.emporio.repository.CategoriaProdottoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CategoriaProdottoService
 */
@Service
public class CategoriaProdottoService {

    @Autowired
    private CategoriaProdottoRepository categoryProductRepository;

    public List<CategoriaProdotto> getAllProductCategories() {
        return categoryProductRepository.findAll();
    }

    public CategoriaProdotto saveProductCategory(CategoriaProdotto productCategory) {
        if (categoryProductRepository.existsByDescription(productCategory.getDescription()))
            throw new EntityExistsException("La categoria " + productCategory.getDescription() + " è già presente!");

        return categoryProductRepository.save(productCategory);
    }
    
}