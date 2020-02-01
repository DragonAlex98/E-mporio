package com.emporio.emporio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "consegna")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Consegna {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdConsegna")
    private Integer idConsegna;

    @Enumerated(EnumType.STRING)
    @Column(name = "StatoConsegna")
    private StatoConsegna statoConsegna;

    @OneToOne(mappedBy = "orderConsegna")
    private Ordine ordine;

    @OneToOne
    private Fattorino fattorino;

    @OneToOne(mappedBy = "consegna")
    private Posto posto;


}