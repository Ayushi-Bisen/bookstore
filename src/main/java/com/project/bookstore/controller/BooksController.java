package com.project.bookstore.controller;

import com.opencsv.bean.CsvToBeanBuilder;
import com.project.bookstore.dto.Book;
import com.project.bookstore.entity.BooksEntity;
import com.project.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
public class BooksController {

    @Autowired
    private BookService bookService;


    @PostMapping(value = "/books", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addBooks(@RequestParam("file") MultipartFile multipartFile) {
        try {
            if (multipartFile.isEmpty()) {
                return ResponseEntity.badRequest().body("File not found");
            }
            if (!multipartFile.getOriginalFilename().contains(".csv")) {
                return ResponseEntity.badRequest().body("Invalid file format. Only csv file supported.");
            }
            List<BooksEntity> books = new CsvToBeanBuilder(new InputStreamReader(multipartFile.getInputStream()))
                    .withSeparator(';')
                    .withType(BooksEntity.class)
                    .build()
                    .parse();
            bookService.addBooks(books);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. error: "+ ex.getMessage());
            return ResponseEntity.badRequest().body("File not found");
        } catch (RuntimeException ex) {
            System.out.println("Invalid csv format. error: "+ ex.getMessage());
            return ResponseEntity.internalServerError().body("Invalid csv format");
        } catch (Exception ex) {
            System.out.println("Error in loading file. error: "+ ex.getMessage());
            return ResponseEntity.internalServerError().body("Error occurred in loading file");
        }
        return ResponseEntity.ok("Books loaded successfully");
    }

    @GetMapping("books")
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String search, @RequestParam(required = false) String isbn){
        if(!isbn.isEmpty()) {
            Book book = bookService.getBookByIsbn(isbn);
            return ResponseEntity.ok(Collections.singletonList(book));
        }

        if (search == null) {
            List<Book> allBooks = bookService.getAllBooks();
            return ResponseEntity.ok(allBooks);
        }

        List<Book> books = bookService.searchBooks(search);
        return ResponseEntity.ok(books);
    }

}
