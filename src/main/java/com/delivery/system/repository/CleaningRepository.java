package com.delivery.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.system.domainclass.Cleaning;
import com.delivery.system.domainclass.Order;
import com.delivery.system.domainclass.Status;

public interface CleaningRepository extends JpaRepository<Cleaning,Long>{

	List<Cleaning> findAll();
	
	Cleaning save(Cleaning cleaning);
	
	int deleteByOrderId(long id);
	
	List<Cleaning> findByStatus(String status);

    List<Cleaning> findByStatus(Status status);
}
