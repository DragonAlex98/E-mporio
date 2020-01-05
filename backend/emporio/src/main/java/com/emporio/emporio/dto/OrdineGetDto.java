package com.emporio.emporio.dto;

import com.emporio.emporio.model.Ordine;

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

    private AttivitaGetDto shop;

    private String customerUsername;

    public static OrdineGetDto parseOrdineToOrdineGetDto(Ordine order) {
        if(order == null)
            return null;

        return OrdineGetDto.builder()
                           .id(order.getOrderId())
                           .parkingAddress(order.getParkingAddress())
                           .shop(AttivitaGetDto.parseAttivitaToAttivitaGetDto(order.getOrderShop()))
                           .customerUsername(order.getOrderCustomer().getUsername())
                           .build();
    }

}