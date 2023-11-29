package com.project.bookstore.service;

import com.project.bookstore.entity.BooksEntity;
import com.project.bookstore.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@WebMvcTest(BookService.class)
@AutoConfigureMockMvc
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    BookRepository bookRepository;

    @Test
    void addBooks() {
        List<BooksEntity> booksEntities = new ArrayList<>();
        booksEntities.add(new BooksEntity("123", "Abc", "Abc", "Some author", "2023", "MImageUrl", "LImageUrl", 2.22, 1, 2.20));
        Mockito.when(bookRepository.saveAll(booksEntities)).thenReturn(booksEntities);
        assertDoesNotThrow(()->bookService.addBooks(booksEntities));
    }

    @Test
    void addBooksThrowsError() {
        List<BooksEntity> booksEntities = new ArrayList<>();
        booksEntities.add(new BooksEntity("123", "Abc", "Abc", "Some author", "2023", "MImageUrl", "LImageUrl", 2.22, 1, 2.20));
        Mockito.when(bookRepository.saveAll(booksEntities)).thenThrow(RuntimeException.class);
        assertThrows(Exception.class, ()->bookService.addBooks(booksEntities));
    }

    @Test
    void getAllBooks() {
    }
}