package com.emporio.emporio.dto;

import com.emporio.emporio.model.Posto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsegnaGetDto {

    private int idConsegna;
    private String statoConsegna;
    private OrdineGetDto ordine;
    private Posto posto;
    
}