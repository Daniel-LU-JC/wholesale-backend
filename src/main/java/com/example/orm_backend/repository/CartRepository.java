package com.example.orm_backend.repository;

import com.example.orm_backend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("select o from Cart o where o.user_id=:userId")
    List<Cart> getBookInCart(@Param("userId") int userId);

    @Transactional
    @Modifying
    @Query(value = "delete from cartitem where user_id=:user_id and book_id=:book_id", nativeQuery = true)
    void deleteOrg(@Param("user_id") int user_id, @Param("book_id") int book_id);

    @Transactional
    @Modifying
    @Query(value = "delete from cartitem where user_id=:user_id", nativeQuery = true)
    void deleteCartItems(@Param("user_id") int user_id);

    @Transactional
    @Modifying
    @Query(value = "insert into cartitem values (:user_id, :book_id, :number);", nativeQuery = true)
    void changeNumber(@Param("user_id") int user_id, @Param("book_id") int book_id, @Param("number") int number);

    @Transactional
    @Modifying
    @Query(value = "insert into cartitem values (:user_id, :book_id, :number);", nativeQuery = true)
    void addToCart(@Param("user_id") int user_id, @Param("book_id") int book_id, @Param("number") int number);

}
