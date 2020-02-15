package com.emporio.emporio.dto;

import com.emporio.emporio.model.CategoriaAttivita;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AttivitaGetDto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttivitaDescrizioneGetDto {

    private String shopPIVA;

    private String shopAddress;

    private String shopBusinessName;
    
    private CategoriaAttivita shopCategory;
    
    private String shopHeadquarter;

	private Float shopLat;

	private Float shopLng;
}