package com.example.orm_backend.service;

import com.example.orm_backend.entity.BookWithNumber;
import com.example.orm_backend.entity.OrderRet;

import java.text.ParseException;
import java.util.List;

public interface OrderService {

    List<OrderRet> getOrdersByUser(int userId);
    List<OrderRet> getOrdersAll();
    List<OrderRet> getOrdersInRange(String start, String end) throws ParseException;
    List<OrderRet> getCertainOrders(int bookId);
    List<BookWithNumber> getBooksByOrder(int orderId);
    void makeOrder(int userId, int price, String time);
    List<OrderRet> getOrdersByBook(int userId, int bookId);
    Integer getOrderNum(int userId);

}
