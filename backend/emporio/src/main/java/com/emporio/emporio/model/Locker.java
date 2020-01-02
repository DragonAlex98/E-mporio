package com.emporio.emporio.model;

import java.util.Map;

import com.emporio.emporio.model.Consegna;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Locker {

    private String address;
    private Map<Posto,Consegna> content;
    private int postiLiberi;

    public int getPostiLiberi(){

        content.forEach((posto,consegna) -> { if (posto.isEmpty()) this.postiLiberi++; });
        return postiLiberi;

    }

}