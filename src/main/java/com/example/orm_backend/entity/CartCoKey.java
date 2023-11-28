package com.example.orm_backend.entity;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
public class CartCoKey implements Serializable {

    private int book_id;
    private int user_id;

    public CartCoKey(Cart bookInCart) {
        this.book_id = bookInCart.getBook_id();
        this.user_id = bookInCart.getUser_id();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CartCoKey a = (CartCoKey) o;
        return Objects.equals(this.book_id, a.book_id) && Objects.equals(this.user_id, a.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.book_id, this.user_id);
    }

}
