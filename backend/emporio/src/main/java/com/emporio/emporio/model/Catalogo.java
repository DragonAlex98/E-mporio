package com.emporio.emporio.model;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="catalogo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Catalogo implements Serializable {
    private static final long serialVersionUID = 8319083102123L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToMany
    @Builder.Default
    private Set<ProdottoDescrizione> products = new HashSet<ProdottoDescrizione>();
}