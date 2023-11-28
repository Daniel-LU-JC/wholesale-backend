package com.example.orm_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class BookMongo {
    @Id
    private int id;
    private String isbn;
    private String book_name;
    private String type;
    private String author;
    private Integer price;
    private String description;
    private Integer inventory;
    private String image;

    public void createInstance(Book book) {
        this.id = book.getId();
        this.isbn = book.getIsbn();
        this.book_name = book.getBook_name();
        this.type = book.getType();
        this.author = book.getAuthor();
        this.price = book.getPrice();
        this.description = book.getDescription();
        this.inventory = book.getInventory();
        this.image = book.getImage();
    }
}
