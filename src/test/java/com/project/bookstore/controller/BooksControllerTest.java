package com.project.bookstore.controller;

import com.project.bookstore.BookService;
import com.project.bookstore.dto.Book;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BooksController.class)
@AutoConfigureMockMvc
class BooksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;


    @Test
    void addBooks() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file","src/main/resources/static/books_details.csv","text/csv",
                new FileInputStream(new File("src/main/resources/static/books_details.csv")));
        mockMvc.perform(multipart("/addBooks").file(multipartFile))
                .andExpect(status().isOk());

    }

    @Test
    void getAllBooks() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.books").isArray());
    }
    @Test
    void shouldReturnBook() throws Exception {
        Mockito.when(bookService.getAllBooks())
                .thenReturn(List.of(new Book(3,"test","test","testurl",100.0,2,"year","descr",2)));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.books[0].name").value("test"));
    }
}