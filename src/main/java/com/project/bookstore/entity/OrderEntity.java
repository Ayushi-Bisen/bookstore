package com.project.bookstore.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "ORDERS")
public class OrderEntity {

    public OrderEntity() {
    }

    public OrderEntity(String orderId, String address, UserEntity userId, String modeOfPayment) {
        this.orderId = orderId;
        this.address = address;
        this.userId = userId;
        this.modeOfPayment = modeOfPayment;
    }

    @Id
    private String orderId;

    private String address;

    @Column(nullable = false, updatable = false, insertable = false)
    private Timestamp orderDate;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private UserEntity userId;

    private String modeOfPayment;

    public String getOrderId() {
        return orderId;
    }
}
