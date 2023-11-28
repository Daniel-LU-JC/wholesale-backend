package com.example.orm_backend.repository;

import com.example.orm_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.user_name=:user_name and u.user_password=:user_password and u.identity=:identity")
    List<User> checkAuth(@Param("user_name") String user_name, @Param("user_password") String user_password, @Param("identity")
                         String identity);
}
