package com.emporio.emporio.util;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

//Classe per la chiave composta della relazione ordine->prodotti
@Embeddable
class OrderDetailsKey implements Serializable {
 
    private static final long serialVersionUID = 158494569579899664L;

    @Column(name = "product_id")
    int productId;

    /*@Column(name = "order_id")
    int orderId;

    public OrderDetailsKey (int productId, int orderId){
        this.productId = productId;
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        OrderDetailsKey that = (OrderDetailsKey) o;
        return Objects.equals(productId, that.productId) &&
               Objects.equals(orderId, that.orderId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(productId, orderId);
    }*/
}
