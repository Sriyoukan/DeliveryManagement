package com.delivery.system.controller;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.system.domainclass.Removal;
import com.delivery.system.domainclass.Removal;
import com.delivery.system.domainclass.Removal;
import com.delivery.system.domainclass.Status;
import com.delivery.system.repository.RemovalRepository;


@RestController
@RequestMapping(value = "/removal")
@CrossOrigin
@Transactional
public class RemovalController {
	   @Autowired
	    private RemovalRepository removalRepository;


	    @Autowired
	    private SimpMessagingTemplate simpMessagingTemplate;

	    @GetMapping("/getAllRemoval")
	    public List<Removal> getAllRemoval(){
	        return removalRepository.findAll();
	    }

	    @PostMapping("/sendRequest")
	    public void send(@RequestBody Removal removal){
	        Removal O1 = saveRemoval(removal);
	        simpMessagingTemplate.convertAndSend("/topic/notification",O1);

	    }

	    @PostMapping("/addRemoval")
	    public Removal  saveRemoval(@RequestBody Removal removal){
	  	    removal.setOrderDate(new Date(System.currentTimeMillis()));
	        removal.setStatus(Status.UNCONFIRMED);
	        Removal O1=removalRepository.save(removal);
	        return O1;
	    }
	    
	    @PostMapping("/deliveredRemoval")
	    public Removal updateToDelivered(@RequestBody Removal removal){
	        removal.setStatus(Status.DELIVERED);
	        Removal O1 = removalRepository.save(removal);
	        return O1;
	    }

	    @GetMapping("/getRemovalByStatus/{status}")
	    public List<Removal> getRemovalByStatus(@PathVariable String status)
	    {
	        Status status1 = Status.UNCONFIRMED ;
	        switch (status){
	            case "UNCONFIRMED":
	                status1= Status.UNCONFIRMED;
	                break;
	            case "CONFIRMED":
	                status1=Status.CONFIRMED;
	                break;
	            case "DELIVERED":
	                status1=Status.DELIVERED;
	                break;

	        }

	        return removalRepository.findByStatus(status1);

	    }
	    @PostMapping("/setConfirm")
	    public Removal confirmOrder(@RequestBody Removal removal){
	        removal.setStatus(Status.CONFIRMED);
	        Removal o1 = removalRepository.save(removal);
	        return o1;
	    }
	    @PostMapping("/deleteRemoval")
	    public void deleteRemoval(@RequestBody Removal removal) throws Exception{
	        try {
	            removalRepository.deleteByOrderId(removal.getOrderId());
	        }catch(Exception ex){
	            System.out.println(ex);
	        }
	    }


}