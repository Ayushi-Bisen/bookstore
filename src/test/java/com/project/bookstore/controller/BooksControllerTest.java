package com.project.bookstore.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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


    @Test
    void addBooks() throws Exception {
        mockMvc.perform(post("/addBooks", new FileInputStream("/Users/ayushi.bisen/Downloads/bookstore/src/main/resources/static/books_details.csv")))
                .andExpect(status().isOk());

    }
}