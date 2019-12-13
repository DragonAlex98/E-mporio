package com.emporio.emporio.model;

import java.util.List;
import com.emporio.emporio.util.OrderDetails;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.emporio.emporio.util.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ordine")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int orderId;
    @Column(name = "Status")
    private OrderStatus status;
    @OneToMany(mappedBy = "ordine")
    List<OrderDetails> orderDetails;
    @Column
    private String parkingAddress;

    //TODO Implementare per il discorso repository
    public Ordine(int orderId, List<OrderDetails> orderDetails, String parkingAddress) {

        this.orderId = orderId;
        this.orderDetails = orderDetails;
        this.parkingAddress = parkingAddress;
        this.status = OrderStatus.DA_RITIRARE;
    }
}