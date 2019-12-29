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
public class ShopAddEmployeeDto implements Serializable {
    
    private static final long serialVersionUID = 7355340422553079735L;

    @NotBlank
    private String employeeUsername;

    @NotBlank
    private String ownerUsername;
}