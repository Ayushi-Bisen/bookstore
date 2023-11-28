package com.project.bookstore;

import com.project.bookstore.dto.Book;
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
    public List<Book> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookEntity -> new Book(bookEntity.getIsbn(),bookEntity.getAuthor(),bookEntity.getName(),bookEntity.getImageUrl(),bookEntity.getPrice(),bookEntity.getBooksAvailable(),bookEntity.getPublicationYear(),bookEntity.getDescription(),bookEntity.getRating()))
                .toList();

    }
}
