package com.example.orm_backend.neo4j;

import com.example.orm_backend.entity.BookWithNumber;
import com.example.orm_backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class Neo4jApplication {

    @Autowired
    private Neo4jBookRepository neo4jBookRepository;

    @Autowired
    private BookService bookService;

    @RequestMapping("/searchByType")
    List<BookWithNumber> searchByType(@RequestParam("type") String bookType) {
        // step 01: get the set of all the book types
        Neo4jBook neo4jBook = neo4jBookRepository.findByLabel(bookType);
        // Then, search for all the labels within two hops
        Set<Neo4jBook> labels = new HashSet<>();
        labels.add(neo4jBook);
        for (Neo4jBook neighbor: neo4jBook.getNeighbors()) {
            labels.add(neighbor);
            labels.addAll(neighbor.getNeighbors());
        }
        labels.forEach(label -> System.out.println(label.getLabel()));

        // step 02: get the books in MySQL according to the book types and return
        List<BookWithNumber> bookWithNumbers = new ArrayList<>();
        for (Neo4jBook neo4jBook1: labels)
            bookWithNumbers.add(bookService.getBooksByType(neo4jBook1.getLabel()).get(0));

        return bookWithNumbers;
    }

    @RequestMapping("/insertData")
    void insertData() {
        neo4jBookRepository.deleteAll();
        Neo4jBook book1 = new Neo4jBook("Java编程");
        Neo4jBook book2 = new Neo4jBook("编程");
        Neo4jBook book3 = new Neo4jBook("C++编程");
        Neo4jBook book4 = new Neo4jBook("外国名著");
        Neo4jBook book5 = new Neo4jBook("中国名著");
        Neo4jBook book6 = new Neo4jBook("世界名著");
        Neo4jBook book7 = new Neo4jBook("武侠小说");
        Neo4jBook book8 = new Neo4jBook("金庸武侠");
        Neo4jBook book9 = new Neo4jBook("古龙武侠");
        book1.neighborWith(book2);
        book2.neighborWith(book1);
        book2.neighborWith(book3);
        book3.neighborWith(book2);
        book4.neighborWith(book6);
        book6.neighborWith(book4);
        book5.neighborWith(book6);
        book6.neighborWith(book5);
        book7.neighborWith(book8);
        book8.neighborWith(book7);
        book7.neighborWith(book9);
        book9.neighborWith(book7);
        book2.neighborWith(book6);
        book6.neighborWith(book2);
        book2.neighborWith(book7);
        book7.neighborWith(book2);
        book6.neighborWith(book7);
        book7.neighborWith(book6);
        List<Neo4jBook> neo4jBooks = Arrays.asList(book1, book2, book3, book4, book5, book6, book7, book8, book9);
        neo4jBookRepository.saveAll(neo4jBooks);
    }
}
