package com.emporio.emporio.dto;

import java.util.List;
import com.emporio.emporio.model.RigaOrdineProdotto;

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

    private List<RigaOrdineProdotto> productsList;
}