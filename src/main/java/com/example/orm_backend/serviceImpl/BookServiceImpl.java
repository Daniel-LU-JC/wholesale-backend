package com.example.orm_backend.serviceImpl;

import com.example.orm_backend.dao.BookDao;
import com.example.orm_backend.entity.Book;
import com.example.orm_backend.entity.BookComparator;
import com.example.orm_backend.entity.BookWithNumber;
import com.example.orm_backend.entity.OrderRet;
import com.example.orm_backend.service.BookService;
import com.example.orm_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    private List<Book> getBooksall() {
        return bookDao.getBooksAll();
    }

    @Override
    public List<BookWithNumber> getBooksAll() {
        List<Book> all_books = bookDao.getBooksAll();
        List<BookWithNumber> book_by_type = new ArrayList<>();

        for (Book book : all_books) {
            BookWithNumber new_book = new BookWithNumber(book.getId(), book.getIsbn(), book.getBook_name(),
                    book.getType(), book.getAuthor(), book.getPrice(), book.getDescription(), book.getInventory(),
                    book.getImage(), 0);
            book_by_type.add(new_book);
        }

        return book_by_type;
    }

    @Override
    public Book getOneBook(int id) {
        return bookDao.findOne(id);
    }

    @Override
    public List<BookWithNumber> getBooksByType(String bookType) {

        List<Book> all_books = getBooksall();
        List<BookWithNumber> book_by_type = new ArrayList<>();

        for (Book book : all_books) {
            if (Objects.equals(book.getType(), bookType)) {
                BookWithNumber new_book = new BookWithNumber(book.getId(), book.getIsbn(), book.getBook_name(),
                        book.getType(), book.getAuthor(), book.getPrice(), book.getDescription(), book.getInventory(),
                        book.getImage(), 0);
                book_by_type.add(new_book);
            }
        }

        return book_by_type;
    }

    @Autowired
    private OrderService orderService;

    private boolean isExists(BookWithNumber book, List<BookWithNumber> book_in_range) {

        boolean flag = false;

        for (BookWithNumber bookWithNumber: book_in_range) {
            if (bookWithNumber.id == book.id) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    @Override
    public List<BookWithNumber> getBooksInRange(int user_id, String start, String end) throws ParseException {
        List<OrderRet> orders_all = orderService.getOrdersInRange(start, end);
        List<BookWithNumber> book_in_range = new ArrayList<>();

        for (OrderRet order: orders_all) {
            if (order.user_id == user_id) {
                List<BookWithNumber> book_tmp = orderService.getBooksByOrder(order.id);
                // 注意将不同订单中的同一类书籍进行合并，主要是数量相加
                for (BookWithNumber book: book_tmp) {
                    // 首先判断当前书籍是否在返回数组中，如果存在则将数量相加，否则直接插入
                    boolean flag = isExists(book, book_in_range);
                    if (flag) {
                        int add = book.number;
                        // 找到同一个 id 值的返回数组元素，并且将其中的 number 值进行增加
                        for (BookWithNumber bookWithNumber: book_in_range) {
                            if (bookWithNumber.id == book.id) {
                                bookWithNumber.number += add;
                                break;
                            }
                        }
                    } else {
                        book_in_range.add(book);
                    }
                }
            }
        }

        return book_in_range;
    }

    @Override
    public List<BookWithNumber> getAllBooksInRange(String start, String end) throws ParseException {
        List<OrderRet> orders_all = orderService.getOrdersInRange(start, end);
        List<BookWithNumber> book_in_range = new ArrayList<>();

        for (OrderRet order: orders_all) {
            List<BookWithNumber> book_tmp = orderService.getBooksByOrder(order.id);
            // 注意将不同订单中的同一类书籍进行合并，主要是数量相加
            for (BookWithNumber book: book_tmp) {
                // 首先判断当前书籍是否在返回数组中，如果存在则将数量相加，否则直接插入
                boolean flag = isExists(book, book_in_range);
                if (flag) {
                    int add = book.number;
                    // 找到同一个 id 值的返回数组元素，并且将其中的 number 值进行增加
                    for (BookWithNumber bookWithNumber: book_in_range) {
                        if (bookWithNumber.id == book.id) {
                            bookWithNumber.number += add;
                            break;
                        }
                    }
                } else {
                    book_in_range.add(book);
                }
            }
        }

        // 【注意】在返回之前需要将书籍按照总收入进行排序，借助于 Comparator
        book_in_range.sort(new BookComparator());

        return book_in_range;
    }

    @Override
    public void addBook(String isbn, String title, String type, String author, int price, String desc, int inventory, String pic) {
        bookDao.addBook(isbn, title, type, author, price, desc, inventory, pic);
    }

    @Override
    public void deleteBook(int id) {
        bookDao.deleteBook(id);
    }

    @Override
    public void editBook(int id, String isbn, String title, String type, String author, int price, String desc, int inventory, String pic) {
        bookDao.editBook(id, isbn, title, type, author, price, desc, inventory, pic);
    }
}
