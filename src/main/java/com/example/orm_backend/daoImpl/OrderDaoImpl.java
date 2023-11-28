package com.example.orm_backend.daoImpl;

import com.example.orm_backend.dao.OrderDao;
import com.example.orm_backend.entity.*;
import com.example.orm_backend.repository.CartRepository;
import com.example.orm_backend.repository.BookRepository;
import com.example.orm_backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Order> getOrdersByUser(int userId) {
        return orderRepository.getOrdersByUser(userId);
    }

    @Override
    public List<Order> getOrdersAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getBooksByOrder(int orderId) {
        return orderRepository.getOne(orderId);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private BookRepository bookRepository;

    private void changeInventory(int book_id, int number_minus) {
        Book book = bookRepository.getById(book_id);
        book.setInventory(book.getInventory() - number_minus);
        bookRepository.saveAndFlush(book);
    }

    @Override
    public int getNumber(int order_id, int book_id) {
        String sql = "SELECT * FROM orderitem WHERE order_id=" + order_id + " and book_id=" + book_id;
        Map findUserId = (Map) jdbcTemplate.queryForMap(sql);
        String number = (String) findUserId.get("number").toString();
        return Integer.parseInt(number);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insert_order(int userId, int price, String time) {
        Order order_tmp = new Order();
        order_tmp.setUser_id(userId);
        order_tmp.setPrice(price);
        order_tmp.setTime(time);

        orderRepository.saveAndFlush(order_tmp);

//        int result = 10 / 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insert_order_item(int userId) {
        int new_order_no = 0;
        List<Order> order_list_tmp = orderRepository.findAll();
        for (Order o : order_list_tmp) {
            if (o.getId() > new_order_no)
                new_order_no = o.getId();
        }

        List<Cart> bookInCart = cartRepository.getBookInCart(userId);
        for (Cart bic : bookInCart) {
            orderRepository.addRecord(new_order_no, bic.getBook_id(), bic.getNumber());
            // 每个循环内部都可以获得一本书的 id 以及购买数量，所以应该在此处对库存量进行修改
            changeInventory(bic.getBook_id(), bic.getNumber());
        }

        cartRepository.deleteCartItems(userId);

//        int result = 10 / 0;
    }

    @Override
    public Integer getOrderNum(int userId) {
        List<Order> order_by_user = orderRepository.getOrdersByUser(userId);
        return order_by_user.size();
    }
}
