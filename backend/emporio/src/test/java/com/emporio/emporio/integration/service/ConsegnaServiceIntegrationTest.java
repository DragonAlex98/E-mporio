package com.emporio.emporio.integration.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.emporio.emporio.model.Consegna;
import com.emporio.emporio.model.Fattorino;
import com.emporio.emporio.repository.ConsegnaRepository;
import com.emporio.emporio.services.ConsegnaService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ConsegnaServiceIntegrationTest
 */
@RunWith(SpringRunner.class)
public class ConsegnaServiceIntegrationTest {

    @TestConfiguration
    static class ConsegnaServiceTestContextConfiguration {

        @Bean
        public ConsegnaService consegnaService() {

            return new ConsegnaService();

        }

    }

    @Autowired
    private ConsegnaService consegnaService;

    @MockBean
    private ConsegnaRepository consegnaRepository;

    @Before
    public void configure() {

        Long idFatt = 2L;
        Consegna delivery = Consegna.builder().idConsegna(1).fattorino(Fattorino.builder().id(idFatt).build()).build();
        List<Consegna> list = new ArrayList<Consegna>();
        list.add(delivery);

        Mockito.when(consegnaRepository.findByfattorino_id(idFatt)).thenReturn(list);

    }

    @Test
    public void whenGetDeliveryByFattorino_ThenReturnList() {

        Fattorino fattorino = Fattorino.builder().id(2L).build();

        List<Consegna> found = consegnaService.getDeliveryByFattorino(fattorino);

        assertTrue(found.size() != 0);

    }


}