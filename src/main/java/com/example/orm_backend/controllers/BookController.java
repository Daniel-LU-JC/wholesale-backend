package com.example.orm_backend.controllers;

import com.example.orm_backend.entity.Book;
import com.example.orm_backend.entity.BookWithNumber;
import com.example.orm_backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/getBooksAll")
    List<BookWithNumber> getBooksAll() {
        return bookService.getBooksAll();
    }

    @RequestMapping("/getOneBook")
    Book getOneBook(@RequestParam("id") String bookId) {
        return bookService.getOneBook(Integer.parseInt(bookId));
    }

    @RequestMapping("/getBooks")
    List<BookWithNumber> getBooksByType(@RequestParam("type") String bookType) {
        return bookService.getBooksByType(bookType);
    }

    @RequestMapping("/getBooksInRange")
    List<BookWithNumber> getBooksInRange(
            @RequestParam("user_id") String user_id,
            @RequestParam("start") String start,
            @RequestParam("end") String end
    ) throws ParseException {
        return bookService.getBooksInRange(Integer.parseInt(user_id), start, end);
    }

    @RequestMapping("/getAllBooksInRange")
    List<BookWithNumber> getAllBooksInRange(
            @RequestParam("start") String start,
            @RequestParam("end") String end
    ) throws ParseException {
        return bookService.getAllBooksInRange(start, end);
    }

    @RequestMapping("/addBook")
    void addBook(
            @RequestParam("isbn") String isbn,
            @RequestParam("title") String title,
            @RequestParam("type") String type,
            @RequestParam("author") String author,
            @RequestParam("price") String price,
            @RequestParam("desc") String desc,
            @RequestParam("inventory") String inventory,
            @RequestParam("pic") String pic
    ) {
        bookService.addBook(isbn, title, type, author, Integer.parseInt(price), desc, Integer.parseInt(inventory), pic);
    }

    @RequestMapping("/deleteBook")
    void deleteBook(@RequestParam("id") String id) {
        bookService.deleteBook(Integer.parseInt(id));
    }

    @RequestMapping("/editBook")
    void editBook(
            @RequestParam("id") String id,
            @RequestParam("isbn") String isbn,
            @RequestParam("title") String title,
            @RequestParam("type") String type,
            @RequestParam("author") String author,
            @RequestParam("price") String price,
            @RequestParam("desc") String desc,
            @RequestParam("inventory") String inventory,
            @RequestParam("pic") String pic
    ) {
        bookService.editBook(Integer.parseInt(id), isbn, title, type, author, Integer.parseInt(price), desc, Integer.parseInt(inventory), pic);
    }
}
