package com.example.orm_backend.dao;

import com.example.orm_backend.entity.Book;

import java.util.List;

public interface BookDaoMongo {
    Book findOne(int id);
    List<Book> getBooksAll();
    void addBook(String isbn, String title, String type, String author, int price, String desc, int inventory, String pic);
    void deleteBook(int id);
    void editBook(int id, String isbn, String title, String type, String author, int price, String desc, int inventory, String pic);
}
