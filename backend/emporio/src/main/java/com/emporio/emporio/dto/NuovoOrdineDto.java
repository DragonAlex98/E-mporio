package com.emporio.emporio.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NuovoOrdineDto implements Serializable {

    private static final long serialVersionUID = 8580965144420192985L;

    @NotBlank
    private String buyerUsername;

    //TODO: Aggiungere altri campi in base a struttura classe ordine
}