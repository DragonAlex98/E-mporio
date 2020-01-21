package com.emporio.emporio.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NuovoLockerDto {

    @NotBlank
    private String address;
    
    @NotNull
    @Min(1)
    private Integer posti;
}