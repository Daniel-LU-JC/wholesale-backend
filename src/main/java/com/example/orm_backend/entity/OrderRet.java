package com.example.orm_backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderRet {

    public int id;
    public Integer user_id;
    public Integer price;
    public String time;

    public OrderRet(int id, Integer user_id, Integer price, String time) {
        this.id = id;
        this.user_id = user_id;
        this.price = price;
        this.time = time;
    }

}
