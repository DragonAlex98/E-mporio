package com.emporio.emporio.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RigaOrdineProdotto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RigaOrdineProdotto {

    @EmbeddedId
    @JsonIgnore
    private ChiaveRigaOrdineProdotto id;

    @ManyToOne
    @MapsId("order_id")
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Ordine order;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private ProdottoDescrizione product;

    @Column(name = "quantity")
    private Integer quantity;
}