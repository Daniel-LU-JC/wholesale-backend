package com.example.orm_backend.daoImpl;

import com.example.orm_backend.dao.BookDao;
import com.example.orm_backend.entity.Book;
import com.example.orm_backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findOne(int id) {
        return bookRepository.getById(id);
    }

    @Override
    public List<Book> getBooksAll() {
        return bookRepository.findAll();
    }

    @Override
    public void addBook(String isbn, String title, String type, String author, int price, String desc, int inventory, String pic) {

        Book add_book = new Book();
        add_book.setIsbn(isbn);
        add_book.setBook_name(title);
        add_book.setType(type);
        add_book.setAuthor(author);
        add_book.setPrice(price);
        add_book.setDescription(desc);
        add_book.setInventory(inventory);
        add_book.setImage(pic);

        bookRepository.saveAndFlush(add_book);
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void editBook(int id, String isbn, String title, String type, String author, int price, String desc, int inventory, String pic) {
        Book add_book = new Book();
        add_book.setId(id);
        add_book.setIsbn(isbn);
        add_book.setBook_name(title);
        add_book.setType(type);
        add_book.setAuthor(author);
        add_book.setPrice(price);
        add_book.setDescription(desc);
        add_book.setInventory(inventory);
        add_book.setImage(pic);

        bookRepository.saveAndFlush(add_book);
    }
}
