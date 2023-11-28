package com.example.orm_backend.service;

import com.example.orm_backend.entity.User;
import com.example.orm_backend.entity.UserWithMoney;

import java.text.ParseException;
import java.util.List;

public interface UserService {

    void register(String name, String password, String email, String address);
    String checkRepeat(String user_name);
    List<User> checkAuth(String name, String password, String identity);
    List<User> getUsersAll();
    void changeState(int user_id);

    List<UserWithMoney> getUsersRanking(String start, String end) throws ParseException;

}
