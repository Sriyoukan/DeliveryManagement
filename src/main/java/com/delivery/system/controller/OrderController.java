package com.delivery.system.controller;


import com.delivery.system.domainclass.Order;
import com.delivery.system.domainclass.ResponseOrder;
import com.delivery.system.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
 
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/getAllOrder")
    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    @PostMapping("/addOrder")
    public ResponseOrder  saveOrder(@RequestBody Order order){
        ResponseOrder r=new ResponseOrder();
        order.setOrderDate(new Date(System.currentTimeMillis()));
        Order O1=orderRepository.save(order);
        r.setOrderId(O1.getOrderId());
        r.setOwnerName(O1.getOwnerName());
        r.setStockAddress(O1.getStockAddress());
        r.setDeliveryAddress(O1.getDeliveryAddress());
        r.setOwnerEmail(O1.getOwnerEmail());
        r.setOwnerPhoneNumber(O1.getOwnerPhoneNumber());
        r.setOrderDate(O1.getOrderDate());
        r.setStatus(O1.getStatus());


        return r;
    }
    
    @GetMapping("/getConfirmOrder/status")
    public List<Order> getConfirmOrder(@RequestParam String status)
    {
    	return orderRepository.findByStatus(status);
    }
    ////
}
