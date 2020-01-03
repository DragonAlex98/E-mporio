package com.emporio.emporio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Posto")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Posto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPosto")
    private int postoId;

    @Column
    private boolean isEmpty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Locker", nullable = false)
    @NotBlank
    private Locker locker;

    @OneToOne
    @JoinColumn(name = "consegna_id", referencedColumnName = "idConsegna")
    private Consegna consegna;


}
