package com.example.orm_backend.entity;

import java.util.Comparator;

public class BookComparator implements Comparator<BookWithNumber> {
    @Override
    public int compare(BookWithNumber o1, BookWithNumber o2) {
        return (o2.number * o2.price) - (o1.number * o1.price);
    }
}
