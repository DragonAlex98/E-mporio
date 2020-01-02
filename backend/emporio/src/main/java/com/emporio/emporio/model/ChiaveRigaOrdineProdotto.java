package com.emporio.emporio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ChiaveRigaOrdineProdotto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ChiaveRigaOrdineProdotto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "order_id")
    Long orderId;

    @Column(name = "product_id")
    Integer productId;

}