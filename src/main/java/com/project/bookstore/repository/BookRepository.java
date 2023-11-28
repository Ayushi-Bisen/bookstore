package com.project.bookstore.repository;

import com.project.bookstore.entity.BooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BooksEntity, Integer> {
}
