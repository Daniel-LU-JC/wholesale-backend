package com.example.orm_backend.serviceImpl;

import com.example.orm_backend.dao.OrderDao;
import com.example.orm_backend.entity.Book;
import com.example.orm_backend.entity.BookWithNumber;
import com.example.orm_backend.entity.Order;
import com.example.orm_backend.entity.OrderRet;
import com.example.orm_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    private int getNumber(int order_id, int book_id) {
        return orderDao.getNumber(order_id, book_id);
    }

    @Override
    public List<OrderRet> getOrdersByUser(int userId) {
        List<Order> order_tmp = orderDao.getOrdersByUser(userId);
        List<OrderRet> order_ret = new ArrayList<>();

        for (Order order: order_tmp) {
            OrderRet new_order = new OrderRet(order.getId(), order.getUser_id(), order.getPrice(), order.getTime());
            order_ret.add(new_order);
        }

        return order_ret;
    }

    @Override
    public List<OrderRet> getOrdersAll() {
        List<Order> order_tmp = orderDao.getOrdersAll();
        List<OrderRet> order_ret = new ArrayList<>();

        for (Order order: order_tmp) {
            OrderRet new_order = new OrderRet(order.getId(), order.getUser_id(), order.getPrice(), order.getTime());
            order_ret.add(new_order);
        }

        return order_ret;
    }

    private boolean IsInRange(String start, String end, String target) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start_date = sdf.parse(start);
        Date end_date = sdf.parse(end);
        Date target_date = sdf.parse(target);

        if (target_date.getTime() == start_date.getTime() || target_date.getTime() == end_date.getTime())
            return true;

        Calendar date = Calendar.getInstance();
        date.setTime(target_date);

        Calendar begin = Calendar.getInstance();
        begin.setTime(start_date);

        Calendar last = Calendar.getInstance();
        last.setTime(end_date);

        return date.after(begin) && date.before(last);
    }

    @Override
    public List<OrderRet> getOrdersInRange(String start, String end) throws ParseException {
        List<Order> order_tmp = orderDao.getOrdersAll();
        List<OrderRet> order_ret = new ArrayList<>();

        for (Order order: order_tmp) {
            boolean flag = IsInRange(start, end, order.getTime());
            if (flag) {
                OrderRet new_order = new OrderRet(order.getId(), order.getUser_id(), order.getPrice(), order.getTime());
                order_ret.add(new_order);
            }
        }

        return order_ret;
    }

    @Override
    public List<OrderRet> getCertainOrders(int bookId) {
        List<Order> order_tmp = orderDao.getOrdersAll();
        List<OrderRet> order_ret = new ArrayList<>();

        for (Order order: order_tmp) {
            boolean flag = false; // true 代表订单中存在目标书籍
            List<Book> books_in_order = order.getBooks_in_order();
            for (Book b : books_in_order) {
                if (b.getId() == bookId) {
                    flag = true;
                    break;
                }
            }

            if (flag) { // 在当前订单中存在一个编号为 bookId 的书籍，则执行下列语句
                OrderRet new_order = new OrderRet(order.getId(), order.getUser_id(), order.getPrice(), order.getTime());
                order_ret.add(new_order);
            }
        }

        return order_ret;
    }

    @Override
    public List<OrderRet> getOrdersByBook(int userId, int bookId) {

        List<Order> order_tmp = orderDao.getOrdersByUser(userId);
        List<OrderRet> order_ret = new ArrayList<>();

        for (Order order: order_tmp) {
            boolean flag = false; // true 代表订单中存在目标书籍
            List<Book> books_in_order = order.getBooks_in_order();
            for (Book b : books_in_order) {
                if (b.getId() == bookId) {
                    flag = true;
                    break;
                }
            }

            if (flag) { // 在当前订单中存在一个编号为 bookId 的书籍，则执行下列语句
                OrderRet new_order = new OrderRet(order.getId(), order.getUser_id(), order.getPrice(), order.getTime());
                order_ret.add(new_order);
            }
        }

        return order_ret;
    }

    @Override
    public List<BookWithNumber> getBooksByOrder(int orderId) {
        Order order = orderDao.getBooksByOrder(orderId);
        List<Book> books = order.getBooks_in_order();
        List<BookWithNumber> bookWithNumbers = new ArrayList<BookWithNumber>();

        for (Book book: books) {
            int number = getNumber(orderId, book.getId());
            BookWithNumber new_book = new BookWithNumber(book.getId(), book.getIsbn(), book.getBook_name(),
                    book.getType(), book.getAuthor(), book.getPrice(), book.getDescription(), book.getInventory(),
                    book.getImage(), number);
            bookWithNumbers.add(new_book);
        }

        return bookWithNumbers;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void makeOrder(int userId, int price, String time) {
        // task 01: insert one record into the order list
        orderDao.insert_order(userId, price, time);

//        int result = 10 / 0;

        // task 02: insert many records into the order items list
        try {
            orderDao.insert_order_item(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        int result = 10 / 0;
    }

    @Override
    public Integer getOrderNum(int userId) {
        return orderDao.getOrderNum(userId);
    }
}
