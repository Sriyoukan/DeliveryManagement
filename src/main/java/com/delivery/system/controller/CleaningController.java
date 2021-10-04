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


import com.delivery.system.domainclass.Cleaning;
import com.delivery.system.domainclass.Status;
import com.delivery.system.repository.CleaningRepository;

@RestController
@RequestMapping(value = "/cleaning")
@CrossOrigin
@Transactional
public class CleaningController {
	
	
    @Autowired
    private CleaningRepository cleaningRepository;


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/getAllCleaning")
    public List<Cleaning> getAllCleaning(){

        return cleaningRepository.findAll();
    }

    @PostMapping("/sendRequest")
    public void send(@RequestBody Cleaning cleaning){
        Cleaning O1 = saveCleaning(cleaning);
        simpMessagingTemplate.convertAndSend("/topic/notification",O1);

    }

    @PostMapping("/addCleaning")
    public Cleaning  saveCleaning(@RequestBody Cleaning cleaning){
        cleaning.setOrderDate(new Date(System.currentTimeMillis()));
        cleaning.setStatus(Status.UNCONFIRMED);
        Cleaning O1=cleaningRepository.save(cleaning);
        return O1;
    }

  

    @PostMapping("/deliveredCleaning")
    public Cleaning updateToDelivered(@RequestBody Cleaning cleaning){
        cleaning.setStatus(Status.DELIVERED);
        Cleaning O1 = cleaningRepository.save(cleaning);
        return O1;
    }

    @GetMapping("/getCleaningByStatus/{status}")
    public List<Cleaning> getCleaningByStatus(@PathVariable String status)
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

        return cleaningRepository.findByStatus(status1);

    }
    @PostMapping("/setConfirm")
    public Cleaning confirmCleaning(@RequestBody Cleaning cleaning){
        cleaning.setStatus(Status.CONFIRMED);
        Cleaning o1 = cleaningRepository.save(cleaning);
        return o1;
    }
    @PostMapping("/deleteCleaning")
    public void deleteCleaning(@RequestBody Cleaning cleaning) throws Exception{
        try {
            cleaningRepository.deleteByOrderId(cleaning.getOrderId());
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
