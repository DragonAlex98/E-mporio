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
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoriaProdotto")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaProdotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int categoryId;

    @Column(name = "Description")
    @NotEmpty(message = "La descrizione non puo essere vuota")
    private String description;

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY)
    private List<Prodotto> products;

    public CategoriaProdotto(int categoryId, String description) {
        this.categoryId = categoryId;
        this.description = description;
    }
}