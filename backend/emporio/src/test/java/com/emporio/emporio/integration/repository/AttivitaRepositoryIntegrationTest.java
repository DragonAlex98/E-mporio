package com.emporio.emporio.integration.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.repository.AttivitaRepository;

/**
 * AttivitaRepositoryIntegrationTest
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class AttivitaRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AttivitaRepository shopRepo;

    @Test
    public void whenFindByShopPIVA_thenReturnAttivita() {
        // Dato
        Attivita shop = Attivita.builder().shopPIVA("P115424448").build();
        entityManager.persistAndFlush(shop);

        // Quando eseguo
        Optional<Attivita> found = shopRepo.findByShopPIVA(shop.getShopPIVA());

        //Allora
        assertTrue(found.isPresent());
        assertEquals(found.get().getShopPIVA(), shop.getShopPIVA());
    }
    
}