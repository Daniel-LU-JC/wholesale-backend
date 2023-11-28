package com.example.orm_backend.repository;

import com.example.orm_backend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {  }
