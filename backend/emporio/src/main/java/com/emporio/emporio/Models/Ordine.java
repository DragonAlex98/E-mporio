package com.emporio.emporio.Models;

import java.util.List;
import com.emporio.emporio.Utils.OrderDetails;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.emporio.emporio.Utils.OrderStatus;

@Entity
@Table(name = "ordine")
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getParkingAddress() {
        return parkingAddress;
    }

    public void setParkingAddress(String parkingAddress) {
        this.parkingAddress = parkingAddress;
    }

    


}