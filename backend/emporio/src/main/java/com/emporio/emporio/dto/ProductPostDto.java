package com.emporio.emporio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProductPostDto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductPostDto {

    private String productName;

    private Double price;
}