package com.emporio.emporio.dto;

import com.emporio.emporio.model.ProdottoDescrizione;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RigaOrdineProdottoDto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RigaOrdineProdottoDto {

    private ProdottoDescrizione product;

    private Integer quantity;
}