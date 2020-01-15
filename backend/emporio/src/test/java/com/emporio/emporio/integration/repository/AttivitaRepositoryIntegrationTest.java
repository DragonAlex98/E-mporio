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
import com.emporio.emporio.repository.AttivitaRepository;
import com.emporio.emporio.repository.CategoriaAttivitaRepository;

/**
 * AttivitaRepositoryIntegrationTest
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@PropertySource(value = "classpath:application-test.properties")
public class AttivitaRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AttivitaRepository shopRepo;

    @Autowired
    private CategoriaAttivitaRepository shopCatRepo;

    @Test
    public void whenFindByShopPIVA_thenReturnAttivita() {
        // Dato
        Attivita shop = this.createShop();

        // Quando eseguo
        Optional<Attivita> found = shopRepo.findByShopDescription_shopPIVA(shop.getShopDescription().getShopPIVA());

        //Allora
        assertTrue(found.isPresent());
        assertEquals(found.get(), shop);
    }

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
    }

    private Attivita createShop() {
        CategoriaAttivita cat = shopCatRepo.save(CategoriaAttivita.builder().shopCategoryDescription("supermercato").build());

        AttivitaDescrizione shopDesc = AttivitaDescrizione.builder().shopPIVA("P115424448")
                                            .shopBusinessName("Superconti")
                                            .shopAddress("Via stretta, 41")
                                            .shopHeadquarter("Milano")
                                            .shopCategory(cat)
                                            .build();

        Catalogo catalog = Catalogo.builder().build();

        Attivita shop = Attivita.builder().catalog(catalog).shopDescription(shopDesc).build();
        return entityManager.persist(shop);
    }
    
}