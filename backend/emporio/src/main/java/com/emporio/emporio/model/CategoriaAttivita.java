package com.emporio.emporio.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoriaAttivita")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaAttivita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    @JsonIgnore
    private int shopCategoryId;

    @Column(name = "Descrizione_Categoria_Attivita")
    @NotBlank(message = "La descrizione dell' attivita' non puo essere vuota")
    private String shopCategoryDescription;

    @JsonIgnore
    @OneToMany(mappedBy = "shopCategory", fetch = FetchType.LAZY)
    private List<AttivitaDescrizione> shops;
}