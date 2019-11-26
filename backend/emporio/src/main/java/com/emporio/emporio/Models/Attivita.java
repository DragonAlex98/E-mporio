package com.emporio.emporio.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "attivita")
public class Attivita {

    @Id
    @Column(name = "Partita_IVA")
    private String shopPIVA;

    @Column(name = "Indirizzo")
    private String shopAddress;

    @Column(name = "Ragione_Sociale")
    private String shopBusinessName;

    @Column(name = "Categoria")
    private int shopCategory;

    @Column(name = "Sede_Operativa")
    private String shopHeadquarter;
/*
    public Attivita(String shopPIVA, String shopAddress, String shopBusinessName, int shopCategory,
            String shopHeadquarter) {
        this.shopPIVA = shopPIVA;
        this.shopAddress = shopAddress;
        this.shopBusinessName = shopBusinessName;
        this.shopCategory = shopCategory;
        this.shopHeadquarter = shopHeadquarter;
    }

    public String getShopPIVA() {
        return shopPIVA;
    }

    public void setShopPIVA(String shopPIVA) {
        this.shopPIVA = shopPIVA;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopBusinessName() {
        return shopBusinessName;
    }

    public void setShopBusinessName(String shopBusinessName) {
        this.shopBusinessName = shopBusinessName;
    }

    public int getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(int shopCategory) {
        this.shopCategory = shopCategory;
    }

    public String getShopHeadquarter() {
        return shopHeadquarter;
    }

    public void setShopHeadquarter(String shopHeadquarter) {
        this.shopHeadquarter = shopHeadquarter;
    }
    
*/    
}