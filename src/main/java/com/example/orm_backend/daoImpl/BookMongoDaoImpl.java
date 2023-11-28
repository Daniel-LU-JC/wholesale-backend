package com.example.orm_backend.daoImpl;

import com.example.orm_backend.dao.BookDao;
import com.example.orm_backend.dao.BookDaoMongo;
import com.example.orm_backend.entity.Book;
import com.example.orm_backend.entity.BookMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BookMongoDaoImpl implements BookDaoMongo {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BookDao bookDao;

    @Override
    public Book findOne(int id) {
        List<Book> books = getBooksAll();
        for (Book book: books)
            if (book.getId() == id)
                return book;
        return null;
    }

    @Override
    public List<Book> getBooksAll() {
        List<BookMongo> bookMongos = mongoTemplate.findAll(BookMongo.class);
        List<Book> books = new ArrayList<>();
        for (BookMongo bookMongo: bookMongos) {
            Book book = new Book();
            book.fromMongo(bookMongo);
            books.add(book);
        }
        return books;
    }

    @Override
    public void addBook(String isbn, String title, String type, String author, int price, String desc, int inventory, String pic) {
        BookMongo bookMongo = new BookMongo();
        List<Book> books = bookDao.getBooksAll();
        for (Book book: books)
            if (Objects.equals(book.getIsbn(), isbn))
                bookMongo.createInstance(book);
        mongoTemplate.insert(bookMongo);
    }

    @Override
    public void deleteBook(int id) {
        BookMongo bookMongo = mongoTemplate.findById(id, BookMongo.class);
        assert bookMongo != null;
        mongoTemplate.remove(bookMongo, "books");
    }

    @Override
    public void editBook(int id, String isbn, String title, String type, String author, int price, String desc, int inventory, String pic) {
        deleteBook(id);
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
        BookMongo bookMongo = new BookMongo();
        bookMongo.createInstance(add_book);
        mongoTemplate.insert(bookMongo);
    }
}
