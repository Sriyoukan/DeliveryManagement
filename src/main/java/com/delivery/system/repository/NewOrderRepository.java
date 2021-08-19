package com.delivery.system.repository;

import com.delivery.system.domainclass.NewOrder;
import com.delivery.system.domainclass.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewOrderRepository extends JpaRepository<NewOrder,Long> {
    List<NewOrder> findAll();

    NewOrder save(NewOrder order);
}
