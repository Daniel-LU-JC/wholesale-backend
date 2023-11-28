package com.example.orm_backend.controllers;

import com.example.orm_backend.entity.BookWithNumber;
import com.example.orm_backend.entity.OrderRet;
import com.example.orm_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/getOrders")
    List<OrderRet> getOrdersByUser(@RequestParam("user_id") String user_id) {
        return orderService.getOrdersByUser(Integer.parseInt(user_id));
    }

    @RequestMapping("/getOrdersAll")
    List<OrderRet> getOrdersAll() {
        return orderService.getOrdersAll();
    }

    @RequestMapping("/getOrdersInRange")
    List<OrderRet> getOrdersInRange(
            @RequestParam("user_id") String user_id,
            @RequestParam("start") String start,
            @RequestParam("end") String end
    ) throws ParseException {
        List<OrderRet> list_tmp = orderService.getOrdersInRange(start, end);
        List<OrderRet> list = new ArrayList<>();
        for (OrderRet orderRet: list_tmp) {
            if (orderRet.user_id == Integer.parseInt(user_id)) {
                list.add(orderRet);
            }
        }
        return list;
    }

    @RequestMapping("/getCertainOrders")
    List<OrderRet> getCertainOrders(@RequestParam("book_id") String book_id) {
        return orderService.getCertainOrders(Integer.parseInt(book_id));
    }

    @RequestMapping("/getOrdersByBook")
    List<OrderRet> getOrdersByBook(
            @RequestParam("user_id") String user_id,
            @RequestParam("book_id") String book_id
    ) {
        return orderService.getOrdersByBook(Integer.parseInt(user_id), Integer.parseInt(book_id));
    }

    @RequestMapping("/getBooksByOrder")
    List<BookWithNumber> getOrderAll(@RequestParam("order_id") String order_id) {
        return orderService.getBooksByOrder(Integer.parseInt(order_id));
    }

//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/makeOrder")
    void makeOrder(
            @RequestParam("user_id") String user_id,
            @RequestParam("price") String price,
            @RequestParam("time") String time
    ) {
//        String message = user_id + "," + price + "," + time;
//        System.out.println(message);  // the format of the message

//        kafkaTemplate.send("pre-process", message);

        orderService.makeOrder(Integer.parseInt(user_id), Integer.parseInt(price), time);
    }

    @RequestMapping("/getOrderNum")
    Integer getOrderNum(
            @RequestParam("user_id") String user_id
    ) {
        return orderService.getOrderNum(Integer.parseInt(user_id));
    }
}
