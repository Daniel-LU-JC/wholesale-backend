package com.example.orm_backend.serviceImpl;

import com.example.orm_backend.dao.CartDao;
import com.example.orm_backend.entity.Book;
import com.example.orm_backend.entity.BookWithNumber;
import com.example.orm_backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    private int getNumber(int user_id, int book_id) {
        return cartDao.getNumber(user_id, book_id);
    }

    @Override
    public List<BookWithNumber> getCartByUserId(int user_id) {
        List<Book> books = cartDao.getCartByUserId(user_id);
        List<BookWithNumber> books_no = new ArrayList<>();

        for (Book book: books) {
            int number = getNumber(user_id, book.getId());
            BookWithNumber new_book = new BookWithNumber(book.getId(), book.getIsbn(), book.getBook_name(),
                    book.getType(), book.getAuthor(), book.getPrice(), book.getDescription(), book.getInventory(),
                    book.getImage(), number);
            books_no.add(new_book);
        }

        return books_no;
    }

    @Override
    public void changeNumber(int user_id, int book_id, int newNumber) {
        cartDao.changeNumber(user_id, book_id, newNumber);
    }

    @Override
    public void deleteItem(int user_id, int book_id) {
        cartDao.deleteItem(user_id, book_id);
    }

    @Override
    public void addToCart(int user_id, int book_id) {
        cartDao.addToCart(user_id, book_id);
    }

}
