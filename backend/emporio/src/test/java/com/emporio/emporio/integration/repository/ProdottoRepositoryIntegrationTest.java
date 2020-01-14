package com.emporio.emporio.integration.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.AttivitaDescrizione;
import com.emporio.emporio.model.Catalogo;
import com.emporio.emporio.model.CategoriaAttivita;
import com.emporio.emporio.model.CategoriaProdotto;
import com.emporio.emporio.model.Prodotto;
import com.emporio.emporio.model.ProdottoDescrizione;
import com.emporio.emporio.repository.AttivitaDescrizioneRepository;
import com.emporio.emporio.repository.AttivitaRepository;
import com.emporio.emporio.repository.CategoriaAttivitaRepository;
import com.emporio.emporio.repository.CategoriaProdottoRepository;
import com.emporio.emporio.repository.ProdottoDescrizioneRepository;
import com.emporio.emporio.repository.ProdottoRepository;

/**
 * AttivitaRepositoryIntegrationTest
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@PropertySource(value = "classpath:application-test.properties")
public class ProdottoRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProdottoDescrizioneRepository prodDescRepo;

    @Autowired
    private ProdottoRepository prodRepo;

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
/* 
    @Test
    public void whenExistsAttivitaByShopPIVA_thenReturnBoolean() {
        // Dato
        Attivita shop = this.createShop();

        //Quando eseguo
        boolean exists = shopRepo.existsAttivitaByShopDescription_shopPIVA(shop.getShopDescription().getShopPIVA());

        //Allora
        assertTrue(exists);

        //Quando eseguo
        shopRepo.delete(shop);
        exists = shopRepo.existsAttivitaByShopDescription_shopPIVA(shop.getShopDescription().getShopPIVA());
        
        //Allora
        assertFalse(exists);
    }

    @Test
    public void whenFindByShopBusinessNameContaining_thenReturnAttivita() {
        //Dato
        Attivita shop = this.createShop();

        //Quando eseguo
        List<Attivita> found = shopRepo.findByShopDescription_shopBusinessNameContaining("Sup");

        //Allora
        assertTrue(found.contains(shop));

    }

    public void whenDeleteByShopPIVA_thenDeleteAttivita() {
        Attivita shop = this.createShop();

        shopRepo.deleteByShopDescription_shopPIVA(shop.getShopDescription().getShopPIVA());

        assertFalse(shopRepo.exists(Example.of(shop)));
    } */

    private Prodotto createShop() {
        CategoriaProdotto cat = prodCatRepo.save(CategoriaProdotto.builder().description("supermercato").build());

        ProdottoDescrizione prodDesc = entityManager.persist(ProdottoDescrizione.builder().productName("salame")
                                                          .productCategory(cat)
                                                          .build());

        Prodotto prod = Prodotto.builder().productDescription(prodDesc).productPrice(15).build();
        return entityManager.persist(prod);
    }
    
}