package com.emporio.emporio.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "attivita")
public class Attivita {

    @Id
    @Column(name = "Partita_IVA")
    private String shopPIVA;

    @Column(name = "Indirizzo")
    private String shopAddress;

    @Column(name = "Ragione_Sociale")
    private String shopBusinessName;

    @Column(name = "Categoria")
    private int shopCategory;

    @Column(name = "Sede_Operativa")
    private String shopHeadquarter;
    
}