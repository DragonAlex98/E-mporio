package com.emporio.emporio.util;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.emporio.emporio.model.Ordine;
import com.emporio.emporio.model.ProdottoDescrizione;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
 
    @EmbeddedId
    private OrderDetailsKey id; // chiave composta
 
    /*@ManyToOne
    @MapsId("product_id") // indica che il campo e' dato da una chiave esterna
    private ProdottoDescrizione prodotto;
 
    @ManyToOne
    @MapsId("order_id")
    private Ordine ordine;
 
    private int quantita; //Quantita'

    public OrderDetails( ProdottoDescrizione prodotto, Ordine ordine) {
        this.prodotto = prodotto;
        this.ordine = ordine;
        this.id = new OrderDetailsKey(prodotto.getProductId(), ordine.getOrderId());
    }*/
}