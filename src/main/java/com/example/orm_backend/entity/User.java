package com.example.orm_backend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class User {

    private int id;
    private String user_name;
    private String user_password;
    private String identity;
    private String email;
    private String address;
    private String state;

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Basic
    @Column(name = "user_name")
    public String getUser_name() { return user_name; }
    public void setUser_name(String user_name) { this.user_name = user_name; }

    @Basic
    @Column(name = "user_password")
    public String getUser_password() { return user_password; }
    public void setUser_password(String user_password) { this.user_password = user_password; }

    @Basic
    @Column(name = "identity")
    public String getIdentity() { return identity; }
    public void setIdentity(String identity) { this.identity = identity; }

    @Basic
    @Column(name = "email")
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Basic
    @Column(name = "address")
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Basic
    @Column(name = "state")
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User that = (User) o;

        if (id != that.id) return false;
        if (!Objects.equals(user_name, that.user_name)) return false;
        if (!Objects.equals(user_password, that.user_password)) return false;
        if (!Objects.equals(identity, that.identity)) return false;
        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(address, that.address)) return false;

        return Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        int result = id;

        result = 31 * result + (user_name!=null? user_name.hashCode() : 0);
        result = 31 * result + (user_password!=null? user_password.hashCode() : 0);
        result = 31 * result + (identity!=null? identity.hashCode() : 0);
        result = 31 * result + (email!=null? email.hashCode() : 0);
        result = 31 * result + (address!=null? address.hashCode() : 0);
        result = 31 * result + (state!=null? state.hashCode() : 0);

        return result;
    }

    // many to many: 每一个用户的购物车中有很多书籍，每一本书籍出现在多个用户的购物车中
    private List<Book> books_in_cart;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cartitem", joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id"))
    public List<Book> getBooks_in_cart() { return books_in_cart; }
    public void setBooks_in_cart(List<Book> books_in_cart) { this.books_in_cart = books_in_cart; }
}
