package com.emporio.emporio.Utils;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.emporio.emporio.Models.Ordine;
import com.emporio.emporio.Models.Prodotto;

@Entity
public class OrderDetails {
 
    @EmbeddedId
    private OrderDetailsKey id; // chiave composta
 
    @ManyToOne
    @MapsId("product_id") // indica che il campo e' dato da una chiave esterna
    private Prodotto prodotto;
 
    @ManyToOne
    @MapsId("order_id")
    private Ordine ordine;
 
    private int quantita; //Quantita'
     
    public OrderDetails() {
        
    }

    public OrderDetails( Prodotto prodotto, Ordine ordine) {
        this.prodotto = prodotto;
        this.ordine = ordine;
        this.id = new OrderDetailsKey(prodotto.getProductId(), ordine.getOrderId());
    }

    public OrderDetailsKey getId() {
        return id;
    }

    public void setId(OrderDetailsKey id) {
        this.id = id;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    };

}