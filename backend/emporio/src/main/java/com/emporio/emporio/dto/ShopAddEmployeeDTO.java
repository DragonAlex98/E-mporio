package com.emporio.emporio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopAddEmployeeDTO {

    private String employeeUsername;
    
    private String ownerUsername;
}