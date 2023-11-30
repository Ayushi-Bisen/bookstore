package com.project.bookstore.service;

import com.project.bookstore.entity.OrderEntity;
import com.project.bookstore.entity.UserEntity;
import com.project.bookstore.repository.OrderRepository;
import com.project.bookstore.repository.UserRepository;
import com.project.bookstore.request.BuyRequest;
import com.project.bookstore.response.BuyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public BuyResponse order(BuyRequest buyRequest) {
        String orderId = UUID.randomUUID().toString();
        UserEntity userEntity = userRepository.getByUsername("ayushi@gmail.com");
        OrderEntity entity = new OrderEntity(orderId, buyRequest.getAddress(), userEntity, buyRequest.getModeOfPayment());

        OrderEntity responseEntity =  orderRepository.save(entity);
        return new BuyResponse(responseEntity.getOrderId());
    }
}
