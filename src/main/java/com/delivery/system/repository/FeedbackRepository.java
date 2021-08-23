package com.delivery.system.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.delivery.system.domainclass.Feedback;


public interface FeedbackRepository extends JpaRepository<Feedback,Long>{
	
	Feedback save(Feedback feedback);
	
	List<Feedback> findAll();
	
	int deleteByFeedbackId(Long id);
}
