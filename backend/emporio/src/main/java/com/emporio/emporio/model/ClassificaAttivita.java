package com.emporio.emporio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "classifica")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassificaAttivita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdClassificaAttivita")
    private Integer idClassificaAttivita;

    @OneToOne
    private AttivitaDescrizione shop;

    @Column(name = "ProdottiTotaliVenduti")
    private Integer productsSold;
}