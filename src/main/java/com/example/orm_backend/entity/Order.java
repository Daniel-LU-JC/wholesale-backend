package com.example.orm_backend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orderlist")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Order {

    private int id;
    private Integer user_id;
    private Integer price;
    private String time;

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Basic
    @Column(name = "user_id")
    public Integer getUser_id() { return user_id; }
    public void setUser_id(Integer user_id) { this.user_id = user_id; }

    @Basic
    @Column(name = "price")
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    @Basic
    @Column(name = "time")
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order that = (Order) o;

        if (id != that.id) return false;
        if (!Objects.equals(user_id, that.user_id)) return false;
        if (!Objects.equals(price, that.price)) return false;

        return Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        int result = id;

        result = 31 * result + (user_id!=null? user_id.hashCode() : 0);
        result = 31 * result + (price!=null? price.hashCode() : 0);
        result = 31 * result + (time!=null? time.hashCode() : 0);

        return result;
    }

    // many to many: 每一个订单中有很多的书籍，每一本书籍出现在多个订单中
    private List<Book> books_in_order;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orderitem", joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id"))
    public List<Book> getBooks_in_order() { return books_in_order; }
    public void setBooks_in_order(List<Book> books_in_order) { this.books_in_order = books_in_order; }
}
