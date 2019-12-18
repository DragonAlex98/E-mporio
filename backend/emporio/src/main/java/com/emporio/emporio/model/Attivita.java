package com.emporio.emporio.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shopCategoryId", nullable = false)
    @NotNull
    CategoriaAttivita shopCategory;

    @Column(name = "Sede_Operativa")
    private String shopHeadquarter;

    @OneToOne(cascade = CascadeType.ALL)
    private Catalogo catalog;
}