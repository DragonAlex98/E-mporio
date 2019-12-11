package com.emporio.emporio.Models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.emporio.emporio.Utils.OrderDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "prodotto")
public class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int productId;

    @Column(name = "Nome")
    private String productName;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    CategoriaProdotto productCategory;

    @Column(name = "Prezzo")
    private double productPrize;

    @Column(name = "Qta")
    private int productQuantity;

    @OneToMany(mappedBy = "prodotto")
    List<OrderDetails> orderDetails;

    public Prodotto() {
        
    }

    public Prodotto(int productId, String productName, CategoriaProdotto productCategory, double productPrize, int productQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productPrize = productPrize;
        this.productQuantity = productQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public CategoriaProdotto getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(CategoriaProdotto productCategory) {
        this.productCategory = productCategory;
    }

    public double getProductPrize() {
        return productPrize;
    }

    public void setProductPrize(double productPrize) {
        this.productPrize = productPrize;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
  
}