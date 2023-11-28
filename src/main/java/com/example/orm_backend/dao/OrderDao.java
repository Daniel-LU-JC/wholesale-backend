package com.example.orm_backend.dao;

import com.example.orm_backend.entity.Order;

import java.util.List;

public interface OrderDao {

    List<Order> getOrdersByUser(int userId);
    List<Order> getOrdersAll();
    Order getBooksByOrder(int orderId);
    int getNumber(int order_id, int book_id);

    Integer getOrderNum(int userId);

    // the following functions are for the transactional management
    void insert_order(int userId, int price, String time);
    void insert_order_item(int userId);

}
