package com.ideanet.bank.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Recipient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long recipientId;
	
	private String recipientName;
	
	private String recipientEmail;
	
	private String recipientPhone;
	
	private String recipientAccountNumber;
	
	private String recipientDescription;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_fk")
	private User user;
	

	public String getRecipientDescription() {
		return recipientDescription;
	}

	public void setRecipientDescription(String recipientDescription) {
		this.recipientDescription = recipientDescription;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(Long recipientId) {
		this.recipientId = recipientId;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	public String getRecipientAccountNumber() {
		return recipientAccountNumber;
	}

	public void setRecipientAccountNumber(String recipientAccountNumber) {
		this.recipientAccountNumber = recipientAccountNumber;
	}
	
	
	

}
