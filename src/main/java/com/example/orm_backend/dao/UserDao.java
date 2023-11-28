package com.example.orm_backend.dao;

import com.example.orm_backend.entity.User;

import java.util.List;

public interface UserDao {

    void register(String name, String password, String email, String address);
    List<User> checkRepeat(String user_name);
    List<User> checkAuth(String name, String password, String identity);
    List<User> getUsersAll();
    void changeState(int user_id);

}
