package com.example.orm_backend.controllers;

import com.example.orm_backend.entity.BookWithNumber;
import com.example.orm_backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping("/getCart")
    List<BookWithNumber> getCartAll(@RequestParam("id") int user_id) {
        return cartService.getCartByUserId(user_id);
    }

    @RequestMapping("/changeNumber")
    void changeNumber(
            @RequestParam("user_id") String user_id,
            @RequestParam("book_id") String book_id,
            @RequestParam("number") String newNumber
    ) {
        cartService.changeNumber(Integer.parseInt(user_id), Integer.parseInt(book_id), Integer.parseInt(newNumber));
    }

    @RequestMapping("/deleteCartItem")
    void deleteCartItem (
            @RequestParam("user_id") String user_id,
            @RequestParam("book_id") String book_id
    ) {
        cartService.deleteItem(Integer.parseInt(user_id), Integer.parseInt(book_id));
    }

    @RequestMapping("/addToCart")
    public void addToCart (
            @RequestParam("user_id") String user_id,
            @RequestParam("book_id") String book_id
    ) {
        cartService.addToCart(Integer.parseInt(user_id), Integer.parseInt(book_id));
    }
}
