package com.emporio.emporio.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OrdineHistoryDto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdineHistoryDto {

    private Long orderId;

    private String parkingAddress;

    private AttivitaDescrizioneGetDto orderShop;

    private String orderCustomerUsername;

    private List<RigaOrdineProdottoDto> orderProductsLineList;

    private String statoConsegna;

    private Integer lockerId;

    private String lockerAddress;

    private String nomePosto;

}