package com.emporio.emporio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attivita_descrizione")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttivitaDescrizione {

    @Id
    @Column(name = "Partita_IVA")
    @NotBlank
    private String shopPIVA;

    @Column(name = "Indirizzo")
    @NotBlank
    private String shopAddress;

    @Column(name = "Ragione_Sociale")
    @NotBlank
    private String shopBusinessName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Categoria_Attivita", nullable = false)
    @NotNull
    private CategoriaAttivita shopCategory;

    @Column(name = "Sede_Operativa")
    @NotBlank
    private String shopHeadquarter;

    @OneToOne(mappedBy = "shopDescription")
    @JsonIgnore
    private Attivita shop;

    @OneToOne(mappedBy = "shop")
    @JsonIgnore
    private ClassificaAttivita classifica;
}