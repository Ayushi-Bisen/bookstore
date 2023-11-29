package com.project.bookstore.controller;

import com.opencsv.bean.CsvToBeanBuilder;
import com.project.bookstore.service.BookService;
import com.project.bookstore.dto.Book;
import com.project.bookstore.dto.Books;
import com.project.bookstore.entity.BooksEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@RestController
@CrossOrigin
public class BooksController {

    @Autowired
    private BookService bookService;


    @PostMapping(value = "/addBooks", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addBooks(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<BooksEntity> books = new CsvToBeanBuilder(new InputStreamReader(multipartFile.getInputStream()))
                .withType(BooksEntity.class)
                .build()
                .parse();
        bookService.addBooks(books);
        return null;
    }

    @GetMapping("books")
    public ResponseEntity<Books> allBooks(){
        List<Book> allBooks = bookService.getAllBooks();
        return ResponseEntity.ok(new Books(allBooks));
    }

}
