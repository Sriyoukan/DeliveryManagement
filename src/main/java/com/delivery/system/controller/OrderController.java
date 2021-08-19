package com.delivery.system.controller;


import com.delivery.system.domainclass.NewOrder;
import com.delivery.system.domainclass.Order;
import com.delivery.system.domainclass.Status;
import com.delivery.system.repository.NewOrderRepository;
import com.delivery.system.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/order")
public class OrderController {
	
	
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private NewOrderRepository newOrderRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/getAllOrder")
    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    @PostMapping("/sendRequest")
    public void send(@RequestBody Order order){
        Order O1 = saveOrder(order);
        simpMessagingTemplate.convertAndSend("/topic/notification",O1);

    }

    @PostMapping("/addOrder")
    public Order  saveOrder(@RequestBody Order order){
        order.setOrderDate(new Date(System.currentTimeMillis()));
        order.setStatus(Status.UNCONFIRMED);
        Order O1=orderRepository.save(order);
        return O1;
    }

    @PostMapping("/addNewOrder")
    public NewOrder saveNewOrder(@RequestBody NewOrder newOrder){
        newOrder.setOrderDate(new Date(System.currentTimeMillis()));
        newOrder.setStatus(Status.UNCONFIRMED);
        NewOrder O1=newOrderRepository.save(newOrder);
        return O1;
    }

    @PostMapping("/deliveredOrder")
    public Order updateToDelivered(@RequestBody Order order){
        order.setStatus(Status.DELIVERED);
        Order O1 = orderRepository.save(order);
        return O1;
    }

    @GetMapping("/getOrderByStatus/{status}")
    public List<Order> getOrderByStatus(@PathVariable String status)
    {
        Status status1 = Status.UNCONFIRMED ;
        switch (status){
            case "UNCONFIRMED":
                status1= Status.UNCONFIRMED;
                break;
            case "CONFIRMED":
                status1=Status.CONFIRMED;
                break;
            case "DELIVERED":
                status1=Status.DELIVERED;
                break;

        }

        return orderRepository.findByStatus(status1);

    }
    //
}
