package com.example.orm_backend.controllers;

import com.example.orm_backend.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @RequestMapping("/getAuthorByBook")
    String getAuthorByBook(@RequestParam("book") String book) {
        return authorService.Book2Author(book);
    }
}
