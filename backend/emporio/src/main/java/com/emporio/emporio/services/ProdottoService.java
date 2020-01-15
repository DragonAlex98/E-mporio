package com.emporio.emporio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Prodotto;
import com.emporio.emporio.repository.ProdottoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProdottoService
 */
@Service
public class ProdottoService {

        @Autowired
        private ProdottoRepository productRepo;

        public Prodotto getProductFrom(String productName) {
            Optional<Prodotto> product = this.productRepo.findByProductDescription_productName(productName);

            if(!product.isPresent())
                throw new EntityNotFoundException("Il prodotto " + productName + " non è presente!");

            return product.get();
        }

        public List<Prodotto> getAllProductsContaining(String name) {
            List<Prodotto> productsList = this.productRepo.findAllByProductDescription_productNameContaining(name);

            if(productsList.isEmpty())
                throw new EntityNotFoundException("Nessun prodotto trovato!");

            return productsList;
        }

		public List<Prodotto> getAllProducts() {
			List<Prodotto> productsList = this.productRepo.findAll();

            if(productsList.isEmpty())
                throw new EntityNotFoundException("Nessun prodotto trovato!");

            return productsList;
		}

		public void deleteProduct(Prodotto product) {
            this.productRepo.delete(product);
        }
        
        public Prodotto saveProdotto(Prodotto product) {
            return this.productRepo.save(product);
        }
    
}