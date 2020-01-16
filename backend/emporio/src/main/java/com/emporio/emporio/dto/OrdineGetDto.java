package com.emporio.emporio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OrdineGetDto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdineGetDto {

    private Long id;

    private String parkingAddress;

    private AttivitaDescrizioneGetDto shop;

    private String customerUsername;

}