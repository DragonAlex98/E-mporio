package com.emporio.emporio.integration.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import com.emporio.emporio.model.CategoriaProdotto;
import com.emporio.emporio.model.Prodotto;
import com.emporio.emporio.model.ProdottoDescrizione;
import com.emporio.emporio.repository.CategoriaProdottoRepository;
import com.emporio.emporio.repository.ProdottoDescrizioneRepository;
import com.emporio.emporio.repository.ProdottoRepository;

/**
 * AttivitaRepositoryIntegrationTest
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class ProdottoRepositoryIntegrationTest {

    @Autowired
    private ProdottoRepository prodRepo;

    @Autowired
    private ProdottoDescrizioneRepository prodDescRepo;

    @Autowired
    private CategoriaProdottoRepository prodCatRepo;

    @Test
    public void whenFindByProductDescription_productName_thenReturnProdotto() {
        // Dato
        Prodotto prod = this.createShop();

        // Quando eseguo
        Optional<Prodotto> found = prodRepo.findByProductDescription_productName(prod.getProductDescription().getProductName());

        //Allora
        assertTrue(found.isPresent());
        assertEquals(found.get(), prod);
    }


    private Prodotto createShop() {
        CategoriaProdotto cat = prodCatRepo.save(CategoriaProdotto.builder().description("supermercato").build());

        ProdottoDescrizione prodDesc = prodDescRepo.save(ProdottoDescrizione.builder().productName("salame")
                                                          .productCategory(cat)
                                                          .build());

        Prodotto prod = Prodotto.builder().productDescription(prodDesc).productPrice(15).build();
        return prodRepo.save(prod);
    }
    
}