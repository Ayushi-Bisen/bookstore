package com.project.bookstore.repository;

import com.project.bookstore.entity.ItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemsEntity, String> {

}
