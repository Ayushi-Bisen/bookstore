package com.project.bookstore.dto;

public record Book(Integer isbn,String author,String name,String imageUrl,Double price,Integer booksAvailable,String publicationYear,String description,String rating)
{

}
