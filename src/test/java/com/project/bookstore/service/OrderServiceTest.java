package com.project.bookstore.service;

import com.project.bookstore.repository.BookRepository;
import com.project.bookstore.repository.ItemRepository;
import com.project.bookstore.repository.OrderRepository;
import com.project.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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

   /* @Test
    void order() {
        Mockito.when(userRepository.getByUsername("abc")).thenReturn(new UserEntity());
        Mockito.when(orderRepository.save(new OrderEntity())).thenReturn(new OrderEntity());
        //Mockito.when(itemRepository.saveAll(Arrays.asList(new ItemsEntity())));
        List<ItemRequest> items = new ArrayList<>();
        items.add(new ItemRequest("123", 1, 2.20));
        BuyRequest buyRequest = new BuyRequest("address", items, "COD", 2.22);
        BuyResponse response = orderService.order(buyRequest);
        assertNotNull(response);
    }*/
}