package com.emporio.emporio.dto;

import javax.validation.constraints.NotNull;

import com.emporio.emporio.model.Posto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsegnaDtoRequest {

    @NotNull
    Long idOrdine;

    @NotNull
    String fattorinoName;

    @NotNull
    int idPosto;

}