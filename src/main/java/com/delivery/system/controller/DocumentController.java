package com.delivery.system.controller;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.delivery.system.domainclass.Document;
import com.delivery.system.repository.DocumentRepository;


@CrossOrigin(origins="*")
@RestController
@RequestMapping(value = "/uploads")
public class DocumentController {


    @Autowired
    private DocumentRepository documentRep;
	@PostMapping("/image")
    public void uploadFile(@RequestParam("document") MultipartFile multipartFile) 
    		throws IOException{
        
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		Document document = new Document();
		document.setName(fileName);
		document.setContent(multipartFile.getBytes());
		document.setSize(multipartFile.getSize());
		Date now = new Date();
		document.setUploadTime(now);
		documentRep.save(document);
    }

	
}
