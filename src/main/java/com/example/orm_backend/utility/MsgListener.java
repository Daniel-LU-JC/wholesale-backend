//package com.example.orm_backend.utility;
//
//import com.example.orm_backend.service.OrderService;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MsgListener {
//
//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    @KafkaListener(topics = "pre-process", groupId = "backend")
//    public void topic1Listener(ConsumerRecord<String, String> record) {
//        String[] value = record.value().split(",");
//        orderService.makeOrder(Integer.parseInt(value[0]),
//                Integer.parseInt(value[1]), value[2]);
//        kafkaTemplate.send("post-process", record.value() + ",Done");
//    }
//
//    @Autowired
//    private WebSocketServer webSocketServer;
//
//    @KafkaListener(topics = "post-process", groupId = "backend")
//    public void topic2Listener(ConsumerRecord<String, String> record) {
//        System.out.println("post-process: " + record.value());
//        String[] value = record.value().split(",");
//        webSocketServer.sendMessageToUser(value[0], value[3]);
//    }
//}
