package com.delivery.system.domainclass;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="feedback")
public class Feedback {
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long feedbackId;

	    @NotNull
	    private String name;

	    @NotNull
	    private String phoneNumber;

	    @NotNull
	    private String email;


	    @NotNull
	    private String message;
	    
	    private int rating;

	    public int getRating() {
			return rating;
		}

		public void setRating(int rating) {
			this.rating = rating;
		}

		public long getFeedbackId() {
			return feedbackId;
		}

		public void setFeedbackId(long feedbackId) {
			this.feedbackId = feedbackId;
		}

		public String getname() {
			return name;
		}

		public void setname(String name) {
			this.name = name;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Date getsentDate() {
			return sentDate;
		}

		public void setsentDate(Date sentDate) {
			this.sentDate = sentDate;
		}

		@NotNull
		 @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	    private Date sentDate;
}
