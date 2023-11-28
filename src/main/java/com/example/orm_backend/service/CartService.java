package com.example.orm_backend.service;

import com.example.orm_backend.entity.BookWithNumber;

import java.util.List;

public interface CartService {

    List<BookWithNumber> getCartByUserId(int user_id);
    void changeNumber(int user_id, int book_id, int newNumber);
    void deleteItem(int user_id, int book_id);
    void addToCart(int user_id, int book_id);

}
