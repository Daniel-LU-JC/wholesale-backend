package com.example.orm_backend.serviceImpl;

import com.example.orm_backend.entity.Book;
import com.example.orm_backend.repository.BookRepository;
import com.example.orm_backend.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public String Book2Author(String BookName) {
        List<Book> books = bookRepository.findAll();
        for (Book book: books) {
            if (Objects.equals(book.getBook_name(), BookName))
                return book.getAuthor();
        }
        return null;
    }
}
