package com.project.bookstore.mapper;

import com.project.bookstore.dto.Book;
import com.project.bookstore.entity.BooksEntity;

public class BooksMapper {

    public static Book mapBookEntity(BooksEntity booksEntity) {
        return new Book(booksEntity.getIsbn(), booksEntity.getAuthor(), booksEntity.getName(), booksEntity.getImageUrlM(), booksEntity.getImageUrlL(), booksEntity.getPrice(), booksEntity.getBooksAvailable(), booksEntity.getPublicationYear(), booksEntity.getDescription(), booksEntity.getRating());
    }
}
