package com.emporio.emporio.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Catalogo_Attivita", nullable = false)
    private Catalogo catalog;

    @OneToMany(mappedBy = "shopEmployed")
    @JsonIgnore
    private List<Dipendente> shopEmployeeList;

    @OneToMany(mappedBy = "shopWorksFor")
    @JsonIgnore
    private List<GestoreMarketing> shopMarketingManagerList;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_attivita", nullable = false)
    private AttivitaDescrizione shopDescription;
}