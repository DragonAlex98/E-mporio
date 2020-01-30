package com.emporio.emporio.integration.service;

import com.emporio.emporio.repository.ConsegnaRepository;
import com.emporio.emporio.services.ConsegnaService;

import org.junit.runner.RunWith;
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


}