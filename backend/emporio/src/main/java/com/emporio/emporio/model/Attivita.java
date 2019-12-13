package com.emporio.emporio.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attivita")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToOne(cascade = CascadeType.ALL)
    private Catalogo catalog;
}