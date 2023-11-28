package com.example.orm_backend.service;

import com.example.orm_backend.entity.Book;
import com.example.orm_backend.entity.BookWithNumber;

import java.text.ParseException;
import java.util.List;

public interface BookService {

    List<BookWithNumber> getBooksAll();
    Book getOneBook(int id);
    List<BookWithNumber> getBooksByType(String bookType);
    List<BookWithNumber> getBooksInRange(int user_id, String start, String end) throws ParseException;
    List<BookWithNumber> getAllBooksInRange(String start, String end) throws ParseException;
    void addBook(String isbn, String title, String type, String author, int price, String desc, int inventory, String pic);
    void deleteBook(int id);
    void editBook(int id, String isbn, String title, String type, String author, int price, String desc, int inventory, String pic);

}
