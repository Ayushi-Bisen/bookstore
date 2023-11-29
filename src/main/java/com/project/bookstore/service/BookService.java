package com.project.bookstore.service;

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

    public void addBooks(List<BooksEntity> books) throws Exception {
        try {
            List<BooksEntity> savedBooks = bookRepository.saveAll(books);
            if (savedBooks.size() != books.size()) {
                throw new Exception("Some books not stored in db");
            }
        } catch (Exception ex) {
           throw new Exception(ex.getMessage());
        }
    }
    public List<Book> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookEntity -> new Book(bookEntity.getIsbn(),bookEntity.getAuthor(),bookEntity.getName(),bookEntity.getImageUrlM(),bookEntity.getImageUrlL(),bookEntity.getPrice(),bookEntity.getBooksAvailable(),bookEntity.getPublicationYear(),bookEntity.getDescription(),bookEntity.getRating()))
                .toList();

    }
}
