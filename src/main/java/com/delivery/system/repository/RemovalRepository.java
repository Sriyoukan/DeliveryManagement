package com.delivery.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.system.domainclass.Removal;
import com.delivery.system.domainclass.Status;



public interface RemovalRepository extends JpaRepository<Removal,Long> {
	 List<Removal> findAll();
	
	Removal save(Removal order);
 

    List<Removal> findByStatus(Status status);

    int deleteByOrderId(long id);

}
