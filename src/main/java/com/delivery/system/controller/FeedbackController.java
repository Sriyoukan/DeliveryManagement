package com.delivery.system.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.system.domainclass.Feedback;
import com.delivery.system.repository.FeedbackRepository;
import java.util.UUID;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
@RestController
@CrossOrigin
@RequestMapping(value = "/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepo;
    
    @GetMapping("/getFeedback")
    public List<Feedback> getFeedback(){
        return feedbackRepo.findAll();
    }

    @PostMapping("/sendFeedback")
    public Feedback send(@RequestBody Feedback feedback){
    	feedback.setsentDate(new Date(System.currentTimeMillis()));
       Feedback feedback1 = feedbackRepo.save(feedback);
       return feedback1;
    }
}
