package com.emporio.emporio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
    @Column(name = "IdConsegna")
    @NotBlank
    private int idConsegna;

    @Column(name = "StatoConsegna")
    @NotBlank
    private StatoConsegna statoConsegna;

    @Column(name = "Ordine")
    @NotBlank
    @OneToOne
    private Ordine ordine;

    @Column(name = "Fattorino")
    @OneToOne
    private User fattorino;


}