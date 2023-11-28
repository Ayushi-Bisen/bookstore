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


    String imageUrl;

    Double price;

    Integer booksAvailable;

    String publicationYear;

    String description;

    Integer rating;
}

