package com.example.orm_backend.repository;

import com.example.orm_backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o from Order o where o.user_id=:userId")
    List<Order> getOrdersByUser(@Param("userId") int userId);

    @Transactional
    @Modifying
    @Query(value = "insert into orderitem values (:order_id, :book_id, :number);", nativeQuery = true)
    void addRecord(@Param("order_id") int order_id, @Param("book_id") int book_id, @Param("number") int number);

}
