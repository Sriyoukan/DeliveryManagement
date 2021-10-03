package com.delivery.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.system.domainclass.Transport;

import com.delivery.system.domainclass.Status;

public interface TransportRepository extends JpaRepository<Transport,Long>{
	
	List<Transport> findAll();
	
	Transport save(Transport transport);
	
	List<Transport> findByStatus(Status status);

	int deleteByOrderId(long orderId);
}
