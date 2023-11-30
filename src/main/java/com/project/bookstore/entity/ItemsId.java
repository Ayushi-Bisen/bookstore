package com.project.bookstore.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ItemsId implements Serializable {
        private Integer orderId;
        private Integer isbn;
}
