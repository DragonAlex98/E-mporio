package com.emporio.emporio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "StatoConsegna")
    private StatoConsegna statoConsegna;

    @OneToOne(mappedBy = "orderConsegna")
    private Ordine ordine;

    @OneToOne
    private User fattorino;


}