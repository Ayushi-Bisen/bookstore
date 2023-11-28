package com.project.bookstore;

import com.project.bookstore.entity.BooksEntity;
import com.project.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


    @Autowired
    public BookRepository bookRepository;

    public void addBooks(List<BooksEntity> books) {
        try {
            bookRepository.saveAll(books);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
