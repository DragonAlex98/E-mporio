package com.emporio.emporio.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "prodotto")
public class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int productId;

    @Column(name = "Nome")
    private String productName;

    @Column(name = "Categoria")
    private int productCategory;

    @Column(name = "Prezzo")
    private double productPrize;

    @Column(name = "Qta")
    private int productQuantity;

}