package com.delivery.system.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.system.domainclass.Feedback;
import com.delivery.system.domainclass.Order;
import com.delivery.system.repository.FeedbackRepository;
import java.util.UUID;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

@RestController
@CrossOrigin
@RequestMapping(value = "/feedback")
@Transactional
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepo;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    
    @GetMapping("/getFeedback")
    public List<Feedback> getFeedback(){
        return feedbackRepo.findAll();
    }

    @PostMapping("/sendFeedback")
    public Feedback send(@RequestBody Feedback feedback){
    	feedback.setsentDate(new Date(System.currentTimeMillis()));
        Feedback feedback1 = feedbackRepo.save(feedback);
        simpMessagingTemplate.convertAndSend("/topic/feedback",feedback1);

       return feedback1;
    }
    @PostMapping("/deleteFeedback")
    public void deleteFeedback(@RequestBody Feedback feedback) throws Exception{
        try {
            feedbackRepo.deleteByFeedbackId(feedback.getFeedbackId());
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
