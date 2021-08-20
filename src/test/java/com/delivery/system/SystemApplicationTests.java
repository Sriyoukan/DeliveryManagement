package com.delivery.system;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.delivery.system.repository.DocumentRepository;
import com.delivery.system.domainclass.Document;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)

class SystemApplicationTests {

	@Autowired
	private DocumentRepository repo;
	@Autowired
	private TestEntityManager entityManager;
    @Test
    @Rollback(false)
    void contextLoads() throws IOException {
    	File file=new File("C:\\Users\\Janish\\Documents\\Downloads\\pre_blue.png");
    	Document document = new Document();
    	document.setName(file.getName());
    	byte[] bytes = Files.readAllBytes(file.toPath());
	
    	document.setContent(bytes);
    	long fileSize = bytes.length;
    	document.setSize(fileSize);
    	Date now = new Date();
    	document.setUploadTime(now);
    	Document saved = repo.save(document);
    	Document existDoc = entityManager.find(Document.class,saved.getId());
    
    	//assertThat(existDoc.getSize()).isEqualTo(fileSize);
    }

}
