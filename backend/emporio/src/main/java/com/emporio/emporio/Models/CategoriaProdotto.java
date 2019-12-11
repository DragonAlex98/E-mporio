package com.emporio.emporio.Models;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categoriaProdotto")
public class CategoriaProdotto {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "Id")
private int categoryId;

@Column(name = "Description")
@NotEmpty(message = "La descrizione non puo essere vuota")
private String description;

@OneToMany(mappedBy = "productCategory")
private List<Prodotto> products;

public CategoriaProdotto() {}

public CategoriaProdotto(int categoryId, String description){
    this.categoryId = categoryId;
    this.description = description;
}

    public int getProductId() {
        return categoryId;
    }

    public void setProductId(int productId) {
        this.categoryId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Prodotto> getProducts() {
        return products;
    }

    public void setProducts(List<Prodotto> products) {
        this.products = products;
    }


}