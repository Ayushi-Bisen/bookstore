package com.project.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BOOK_DETAILS")
public class BooksEntity {

    @Id
    Integer isbn;

    String author;

    String name;

    @Column(name = "image_url")
    String imageUrl;

    Double price;

    @Column(name = "books_available")
    Integer booksAvailable;

    @Column(name = "publication_year")
    String publicationYear;

    String description;

    String rating;
}
