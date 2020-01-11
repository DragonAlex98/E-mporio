package com.emporio.emporio.integration.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import com.emporio.emporio.model.Attivita;
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
        CategoriaAttivita cat = shopCatRepo.save(CategoriaAttivita.builder().shopCategoryDescription("supermercato").build());

        Catalogo c1 = Catalogo.builder().build();

        Attivita shop = Attivita.builder().shopPIVA("P115424448")
                                            .shopBusinessName("Superconti")
                                            .shopAddress("Via stretta, 41")
                                            .shopHeadquarter("Milano")
                                            .shopCategory(cat)
                                            .catalog(c1)
                                            .build();
        entityManager.persist(shop);

        // Quando eseguo
        Optional<Attivita> found = shopRepo.findByShopPIVA(shop.getShopPIVA());

        //Allora
        assertTrue(found.isPresent());
        assertEquals(found.get().getShopPIVA(), shop.getShopPIVA());
    }
    
}