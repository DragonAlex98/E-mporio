package com.emporio.emporio.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.emporio.emporio.dto.AttivitaGetDto;
import com.emporio.emporio.dto.RegistrazioneAttivitaDto;
import com.emporio.emporio.model.Attivita;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

/**
 * AttivitaControllerUnitTests
 */
public class AttivitaControllerUnitTests {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertAttivitaEntityToAttivitaGetDto_thenCorrect() {
        Attivita shop = Attivita.builder().shopPIVA("aaa111")
                                          .shopBusinessName("Superconti")
                                          .shopAddress("Via Sassotetto, 11")
                                          .shopHeadquarter("shopHeadquarter")
                                          .build();

        AttivitaGetDto shopDto = modelMapper.map(shop, AttivitaGetDto.class);
        assertTrue(shop.getShopPIVA().equals(shopDto.getShopPIVA()));
        assertTrue(shop.getShopBusinessName().equals(shopDto.getShopBusinessName()));
        assertTrue(shop.getShopAddress().equals(shopDto.getShopAddress()));
        assertTrue(shop.getShopHeadquarter().equals(shopDto.getShopHeadquarter()));
    }

    @Test
    public void whenConvertAttivitaGetDtoToAttivitaEntity_thenCorrect() {
        RegistrazioneAttivitaDto shopDto = RegistrazioneAttivitaDto.builder().shopBusinessName("Superconti")
                                                                             .shopCategoryDescription("utensili")
                                                                             .build();
 
        Attivita shop = modelMapper.map(shopDto, Attivita.class);
        assertTrue(shopDto.getShopBusinessName().equals(shop.getShopBusinessName()));
        assertTrue(shopDto.getShopCategoryDescription().equals(shop.getShopCategory().getShopCategoryDescription()));
    }
}