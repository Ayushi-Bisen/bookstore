package com.project.bookstore.controller;

import com.project.bookstore.request.BuyRequest;
import com.project.bookstore.response.BuyResponse;
import com.project.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class OrdersController {

    @Autowired
    private OrderService orderService;


    @PostMapping(value = "/order")
    public ResponseEntity<BuyResponse> order(@RequestBody BuyRequest buyRequest) {
        BuyResponse buyResponse = null;
        try {
            buyResponse = orderService.order(buyRequest);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(buyResponse);
    }

}
