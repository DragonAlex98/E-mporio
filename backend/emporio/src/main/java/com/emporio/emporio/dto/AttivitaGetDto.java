package com.emporio.emporio.dto;

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
public class AttivitaGetDto {

    private AttivitaDescrizioneGetDto shopDescription;

}