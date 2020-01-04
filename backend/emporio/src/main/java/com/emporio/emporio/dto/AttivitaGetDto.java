package com.emporio.emporio.dto;

import com.emporio.emporio.model.Attivita;
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
public class AttivitaGetDto {

    private String shopPIVA;

    private String shopAddress;

    private String shopBusinessName;
    
    private CategoriaAttivita shopCategory;
    
    private String shopHeadquarter;

    public static AttivitaGetDto parseAttivitaToAttivitaGetDto(Attivita shop) {
        if(shop == null)
            return null;
            
        return AttivitaGetDto.builder()
                             .shopPIVA(shop.getShopPIVA())
                             .shopAddress(shop.getShopAddress())
                             .shopBusinessName(shop.getShopBusinessName())
                             .shopCategory(shop.getShopCategory())
                             .shopHeadquarter(shop.getShopHeadquarter())
                             .build();
    }
}