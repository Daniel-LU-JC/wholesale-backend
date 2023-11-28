package com.example.orm_backend.daoImpl;

import com.example.orm_backend.dao.CartDao;
import com.example.orm_backend.entity.*;
import com.example.orm_backend.repository.CartRepository;
import com.example.orm_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Book> getCartByUserId(int user_id) {
        User user = userRepository.getOne(user_id);
        return user.getBooks_in_cart();
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void changeNumber(int user_id, int book_id, int newNumber) {
        cartRepository.deleteOrg(user_id, book_id);
        cartRepository.changeNumber(user_id, book_id, newNumber);
    }

    @Override
    public int getNumber(int user_id, int book_id) {
        String sql = "SELECT * FROM cartitem WHERE user_id=" + user_id + " and book_id=" + book_id;
        Map findUserId = (Map) jdbcTemplate.queryForMap(sql);
        String number = (String) findUserId.get("number").toString();
        return Integer.parseInt(number);
    }

    @Override
    public void deleteItem(int user_id, int book_id) {
        cartRepository.deleteOrg(user_id, book_id);
    }

    @Override
    public void addToCart(int user_id, int book_id) {
        cartRepository.addToCart(user_id, book_id, 1);
    }
}
