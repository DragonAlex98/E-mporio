package com.emporio.emporio.integration.repository;

import com.emporio.emporio.model.CategoriaAttivita;
import com.emporio.emporio.repository.CategoriaAttivitaRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * CategoriaAttivitaRepositoryIntegrationTest
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class CategoriaAttivitaRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoriaAttivitaRepository shopCatRepo;

    @Test
    public void whenFindByShopCategoryDescription_thenReturnCategoriaAttivita() {
        CategoriaAttivita shopCat = this.createCategoriaAttivita();

        CategoriaAttivita found = shopCatRepo.findByShopCategoryDescription(shopCat.getShopCategoryDescription()).orElse(null);

        assertEquals(shopCat, found);
    }

    @Test
    public void whenExistsByShopCategoryDescription_thenReturnBoolean() {
        CategoriaAttivita shopCat = this.createCategoriaAttivita();

        boolean exists = shopCatRepo.existsByShopCategoryDescription(shopCat.getShopCategoryDescription());

        assertTrue(exists);

        shopCatRepo.delete(shopCat);
        exists = shopCatRepo.existsByShopCategoryDescription(shopCat.getShopCategoryDescription());

        assertFalse(exists);
    }

    private CategoriaAttivita createCategoriaAttivita() {
        CategoriaAttivita shopCat = CategoriaAttivita.builder().shopCategoryDescription("utensili").build();

        return entityManager.persistAndFlush(shopCat);
    }
    
}