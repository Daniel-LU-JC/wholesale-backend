package com.example.orm_backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserWithMoney {

    public int id;
    public String user_name;
    public String user_password;
    public String identity;
    public String email;
    public String address;
    public String state;

    // 记录每个用户购买书籍消费的总金额，初始化为 0
    public int money;

    public UserWithMoney(int id, String user_name, String user_password, String identity, String email, String address, String state) {
        this.id = id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.identity = identity;
        this.email = email;
        this.address = address;
        this.state = state;
        this.money = 0;
    }

}
