package com.delivery.system.repository;

import com.delivery.system.domainclass.Order;
import com.delivery.system.domainclass.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAll();

    Order save(Order order);
    
    List<Order> findByStatus(String status);

    List<Order> findByStatus(Status status);

    int deleteByOrderId(long id);


}
