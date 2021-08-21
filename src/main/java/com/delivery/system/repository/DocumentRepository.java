package com.delivery.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery.system.domainclass.Document;

public interface DocumentRepository extends JpaRepository<Document,Long>{
	
	Document save(Document document);
}
