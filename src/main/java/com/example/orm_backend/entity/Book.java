package com.example.orm_backend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "book")
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Book {

    private int id;
    private String isbn;
    private String book_name;
    private String type;
    private String author;
    private Integer price;
    private String description;
    private Integer inventory;
    private String image;

    public void fromMongo(BookMongo bookMongo) {
        this.id = bookMongo.getId();
        this.isbn = bookMongo.getIsbn();
        this.book_name = bookMongo.getBook_name();
        this.type = bookMongo.getType();
        this.author = bookMongo.getAuthor();
        this.price = bookMongo.getPrice();
        this.description = bookMongo.getDescription();
        this.inventory = bookMongo.getInventory();
        this.image = bookMongo.getImage();
    }

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Basic
    @Column(name = "isbn")
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    @Basic
    @Column(name = "book_name")
    public String getBook_name() { return book_name; }
    public void setBook_name(String book_name) { this.book_name = book_name; }

    @Basic
    @Column(name = "type")
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Basic
    @Column(name = "author")
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    @Basic
    @Column(name = "price")
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    @Basic
    @Column(name = "description")
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Basic
    @Column(name = "inventory")
    public Integer getInventory() { return inventory; }
    public void setInventory(Integer inventory) { this.inventory = inventory; }

    @Basic
    @Column(name = "image")
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Book that = (Book) o;

        if (id != that.id) return false;
        if (!Objects.equals(isbn, that.isbn)) return false;
        if (!Objects.equals(book_name, that.book_name)) return false;
        if (!Objects.equals(type, that.type)) return false;
        if (!Objects.equals(author, that.author)) return false;
        if (!Objects.equals(price, that.price)) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (!Objects.equals(inventory, that.inventory)) return false;

        return Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        int result = id;

        result = 31 * result + (isbn!=null? isbn.hashCode() : 0);
        result = 31 * result + (book_name!=null? book_name.hashCode() : 0);
        result = 31 * result + (type!=null? type.hashCode() : 0);
        result = 31 * result + (author!=null? author.hashCode() : 0);
        result = 31 * result + (price!=null? price.hashCode() : 0);
        result = 31 * result + (description!=null? description.hashCode() : 0);
        result = 31 * result + (inventory!=null? inventory.hashCode() : 0);
        result = 31 * result + (image!=null? image.hashCode() : 0);

        return result;
    }

    // many to many: 每一个订单中有很多的书籍，每一本书籍出现在多个订单中
    private List<Order> order_with_book;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "books_in_order")
    public List<Order> getOrder_with_book() { return order_with_book; }
    public void setOrder_with_book(List<Order> order_with_book) { this.order_with_book = order_with_book; }

}
