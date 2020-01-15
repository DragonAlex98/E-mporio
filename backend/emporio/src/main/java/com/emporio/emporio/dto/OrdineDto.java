package com.emporio.emporio.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdineDto {

    private String customerUsername;

    private String employeeUsername;

    private String carPosition;

    private Map<String, Integer> lines;
}