package com.project.bookstore.controller;

import com.project.bookstore.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BooksController.class)
@AutoConfigureMockMvc
class BooksControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;


    @Test
    void addBooks() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file","/Users/ayushi.bisen/Downloads/bookstore/src/main/resources/static/books_details.csv","text/csv",
                new FileInputStream(new File("/Users/ayushi.bisen/Downloads/bookstore/src/main/resources/static/books_details.csv")));
        mockMvc.perform(multipart("/addBooks").file(multipartFile))
                .andExpect(status().isOk());

    }
}