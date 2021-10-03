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

import com.delivery.system.domainclass.Status;
import com.delivery.system.domainclass.Transport;
import com.delivery.system.repository.TransportRepository;

@RestController
@RequestMapping("/transport")
@Transactional
@CrossOrigin
public class TransportController {
	@Autowired
	private TransportRepository transportRepository;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@GetMapping("/getAllTransport")
	public List<Transport> getAllTransport()
	{
		return transportRepository.findAll();
	}
	
	
	@PostMapping("/sendRequest")
	public void send(@RequestBody Transport transport)
	{
		Transport t = saveTransport(transport);
		simpMessagingTemplate.convertAndSend("/topic/notification",t);
	}
	
	@PostMapping("/addTransport")
	public Transport saveTransport(@RequestBody Transport transport)
	{
		transport.setOrderDate(new Date(System.currentTimeMillis()));
		transport.setStatus(Status.UNCONFIRMED);
		Transport transport1 = transportRepository.save(transport);
		return transport1;
	}
	
	@PostMapping("/deliveredTransport")
	public Transport updateToDelivered(@RequestBody Transport transport)
	{
		transport.setStatus(Status.DELIVERED);
		Transport transport1 = transportRepository.save(transport);
		return transport1;
		
	}
	
	@GetMapping("/getTransportByStatus/{status}")
    public List<Transport> getTransportByStatus(@PathVariable String status)
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

        return transportRepository.findByStatus(status1);

    }
    @PostMapping("/setConfirm")
    public Transport confirmOrder(@RequestBody Transport transport){
        transport.setStatus(Status.CONFIRMED);
        Transport o1 = transportRepository.save(transport);
        return o1;
    }
    @PostMapping("/deleteTransport")
    public void deleteTransport(@RequestBody Transport transport) throws Exception{
        try {
            transportRepository.deleteByOrderId(transport.getOrderId());
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
	
}
