package com.emporio.emporio.unit.mappings;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.emporio.emporio.dto.AttivitaDescrizioneGetDto;
import com.emporio.emporio.dto.RegistrazioneAttivitaDto;
import com.emporio.emporio.model.AttivitaDescrizione;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

/**
 * AttivitaControllerUnitTests
 */
public class AttivitaMappingUnitTests {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertAttivitaDescrizioneEntityToAttivitaDescrizioneGetDto_thenCorrect() {
        AttivitaDescrizione shop = AttivitaDescrizione.builder().shopPIVA("aaa111")
                                          .shopBusinessName("Superconti")
                                          .shopAddress("Via Sassotetto, 11")
                                          .shopHeadquarter("shopHeadquarter")
                                          .build();

        AttivitaDescrizioneGetDto shopDto = modelMapper.map(shop, AttivitaDescrizioneGetDto.class);
        assertTrue(shop.getShopPIVA().equals(shopDto.getShopPIVA()));
        assertTrue(shop.getShopBusinessName().equals(shopDto.getShopBusinessName()));
        assertTrue(shop.getShopAddress().equals(shopDto.getShopAddress()));
        assertTrue(shop.getShopHeadquarter().equals(shopDto.getShopHeadquarter()));
    }

    @Test
    public void whenConvertRegistrazioneAttivitaDtoToAttivitaDescrizioneEntity_thenCorrect() {
        RegistrazioneAttivitaDto shopDto = RegistrazioneAttivitaDto.builder().shopBusinessName("Superconti")
                                                                             .shopCategoryDescription("utensili")
                                                                             .build();
 
        AttivitaDescrizione shop = modelMapper.map(shopDto, AttivitaDescrizione.class);
        assertTrue(shopDto.getShopBusinessName().equals(shop.getShopBusinessName()));
        assertTrue(shopDto.getShopCategoryDescription().equals(shop.getShopCategory().getShopCategoryDescription()));
    }
}