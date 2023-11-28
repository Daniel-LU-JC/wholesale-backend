package com.example.orm_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cartitem")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@IdClass(value = CartCoKey.class)
public class Cart {

    @Id
    @Column(name = "user_id")
    private int user_id;

    @Id
    @Column(name = "book_id")
    private int book_id;

    private int number;
}
