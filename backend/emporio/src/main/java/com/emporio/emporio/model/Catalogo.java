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
public class Catalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @Builder.Default
    private Set<Prodotto> products = new HashSet<Prodotto>();

    public boolean containsProduct(String productName) {
        return this.products.stream()
                            .anyMatch(prod -> prod.getProductDescription().getProductName().equals(productName));
    }
}