package com.project.bookstore.dto;

import java.util.List;

public record Order(String orderId, String address, String modeOfPayment, List<Item> items, String orderDate,
                    Double totalPrice) {
}
