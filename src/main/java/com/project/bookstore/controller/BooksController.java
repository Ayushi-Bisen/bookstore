package com.project.bookstore.controller;

import com.project.bookstore.BookService;
import jakarta.ws.rs.Consumes;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
public class BooksController {

    @Autowired
    private BookService bookService;


    @PostMapping("/addBooks")
    @Consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addBooks(@FormDataParam("file") InputStream inputStream) {
        return null;
    }

}
