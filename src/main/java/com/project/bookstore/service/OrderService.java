package com.project.bookstore.service;

import com.project.bookstore.entity.ItemsEntity;
import com.project.bookstore.entity.OrderEntity;
import com.project.bookstore.entity.UserEntity;
import com.project.bookstore.repository.BookRepository;
import com.project.bookstore.repository.ItemRepository;
import com.project.bookstore.repository.OrderRepository;
import com.project.bookstore.repository.UserRepository;
import com.project.bookstore.request.BuyRequest;
import com.project.bookstore.response.BuyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ItemRepository itemRepository;

    public BuyResponse order(String username, BuyRequest buyRequest) {
        String orderId = UUID.randomUUID().toString();
        UserEntity userEntity = userRepository.getByUsername(username);
        OrderEntity orderEntity = new OrderEntity(orderId, buyRequest.getAddress(), userEntity, buyRequest.getModeOfPayment());

        List<ItemsEntity> itemsEntities = buyRequest.getItems().stream().map((item) -> {
             return new ItemsEntity(orderId, item.isbn(), item.quantity());
        }).toList();

        OrderEntity responseEntity =  orderRepository.save(orderEntity);
        itemRepository.saveAll(itemsEntities);
        return new BuyResponse(responseEntity.getOrderId());
    }
}
