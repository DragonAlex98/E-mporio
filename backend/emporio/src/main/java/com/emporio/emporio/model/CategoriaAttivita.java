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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoriaAttivita")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaAttivita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int shopCategoryId;

    @Column(name = "ShopCategoryDescription")
    @NotEmpty(message = "La descrizione dell' attivita' non puo essere vuota")
    private String shopCategoryDescription;

    @OneToMany(mappedBy = "shopCategory", fetch = FetchType.LAZY)
    private List<Attivita> shops ;

    public int getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(int shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public String getShopCategoryDescription() {
        return shopCategoryDescription;
    }

    public void setShopCategoryDescription(String shopCategoryDescription) {
        this.shopCategoryDescription = shopCategoryDescription;
    }

    @JsonIgnore
    public List<Attivita> getShops() {
        return shops;
    }

    public void setShops(List<Attivita> shops) {
        this.shops = shops;
    }

    

}