package com.project.bookstore.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "ITEMS")
public class ItemsEntity {

    @EmbeddedId
    private ItemsId id;

    @OneToOne @MapsId("orderId")
    private OrderEntity orderId;

    @ManyToOne @MapsId("isbn")
    private BooksEntity isbn;

    private Integer quantity;

}
