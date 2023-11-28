package com.example.orm_backend.dao;

import com.example.orm_backend.entity.Book;

import java.util.List;

public interface CartDao {

    List<Book> getCartByUserId(int user_id);
    void changeNumber(int user_id, int book_id, int newNumber);
    void deleteItem(int user_id, int book_id);
    void addToCart(int user_id, int book_id);
    int getNumber(int user_id, int book_id);

}
