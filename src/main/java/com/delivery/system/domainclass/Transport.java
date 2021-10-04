package com.delivery.system.domainclass;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="TransportOrders")
public class Transport {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;
	
	@NotNull
	private String email;
	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFrightfrom() {
		return frightFrom;
	}

	public void setFrightfrom(String frightFrom) {
		this.frightFrom = frightFrom;
	}

	public String getFrightTo() {
		return frightTo;
	}

	public void setFrightTo(String frightTo) {
		this.frightTo = frightTo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	private String phone;
	
	@NotNull
	private String frightFrom;
	
	@NotNull
	private String frightTo;
	
	@NotNull
	private String address;
	
	@NotNull
	private String description;
	
	private Status status;
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	private Date orderDate;
 
}
