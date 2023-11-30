package com.project.bookstore.service;

import com.project.bookstore.entity.OrderEntity;
import com.project.bookstore.entity.UserEntity;
import com.project.bookstore.repository.BookRepository;
import com.project.bookstore.repository.ItemRepository;
import com.project.bookstore.repository.OrderRepository;
import com.project.bookstore.repository.UserRepository;
import com.project.bookstore.request.BuyRequest;
import com.project.bookstore.request.ItemRequest;
import com.project.bookstore.response.BuyResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(OrderService.class)
@AutoConfigureMockMvc
class OrderServiceTest {
    @MockBean
    BookRepository bookRepository;

    @Autowired
    private OrderService orderService;
    @MockBean
    UserRepository userRepository;

    @MockBean
    ItemRepository itemRepository;

    @MockBean
    OrderRepository orderRepository;

    @Test
    void orderSuccessful() throws Exception {
        Mockito.when(userRepository.getByUsername("username")).thenReturn(new UserEntity());
        OrderEntity orderEntity = new OrderEntity("123", "address", new UserEntity(), "COD");
        Mockito.when(orderRepository.save(any())).thenReturn(orderEntity);
        List<ItemRequest> items = new ArrayList<>();
        items.add(new ItemRequest("123", 1, 2.20));
        BuyRequest buyRequest = new BuyRequest("address", items, "COD", 2.22);
        BuyResponse response = orderService.order("username", buyRequest);
        assertEquals("123", response.orderId());
    }

    @Test
    void orderFailureInUserNotFound() {
        Mockito.when(userRepository.getByUsername("username")).thenThrow(RuntimeException.class);
        List<ItemRequest> items = new ArrayList<>();
        items.add(new ItemRequest("123", 1, 2.20));
        BuyRequest buyRequest = new BuyRequest("address", items, "COD", 2.22);
        assertThrows(Exception.class, () -> orderService.order("username", buyRequest));
    }

    @Test
    void orderFailureOnItemsEmpty() {
        Mockito.when(userRepository.getByUsername("username")).thenReturn(new UserEntity());
        List<ItemRequest> items = new ArrayList<>();
        BuyRequest buyRequest = new BuyRequest("address", items, "COD", 2.22);
        assertThrows(Exception.class, () -> orderService.order("username", buyRequest));
    }

    @Test
    void orderFailureOnOrdersNotStored() {
        Mockito.when(userRepository.getByUsername("username")).thenReturn(new UserEntity());
        Mockito.when(orderRepository.save(any())).thenThrow(RuntimeException.class);
        List<ItemRequest> items = new ArrayList<>();
        items.add(new ItemRequest("123", 1, 2.20));
        BuyRequest buyRequest = new BuyRequest("address", items, "COD", 2.22);
        assertThrows(Exception.class, ()-> orderService.order("username", buyRequest));
    }
}