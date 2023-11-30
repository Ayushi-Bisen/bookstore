package com.project.bookstore.controller;

import com.project.bookstore.request.BuyRequest;
import com.project.bookstore.response.BuyResponse;
import com.project.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class OrdersController {

    @Autowired
    private OrderService orderService;


    @PostMapping(value = "/order")
    public ResponseEntity<?> order(@RequestAttribute("username") String username, @RequestBody BuyRequest buyRequest) {
        if (username == null || username.equals("")) {
            String body =  "{\"errCode\": \"INTERNAL_SERVER_ERROR\", \"message\":\"username expected in context\"}";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }

        try {
            BuyResponse buyResponse = orderService.order(username, buyRequest);
            return ResponseEntity.ok(buyResponse);
        } catch (Exception ex) {
            String body =  "{\"errCode\": \"INTERNAL_SERVER_ERROR\", \"message\":\"" + ex.getMessage() + "\"}";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

}
