package com.delivery.system.controller;


import com.delivery.system.domainclass.Order;
import com.delivery.system.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Order saveOrder(@RequestBody Order order){
        return orderRepository.save(order);
    } 
    
    @GetMapping("/getConfirmOrder/status")
    public List<Order> getConfirmOrder(@RequestParam String status)
    {
    	return orderRepository.findByStatus(status);
    }
    //////
}
