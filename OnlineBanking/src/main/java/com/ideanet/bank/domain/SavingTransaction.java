package com.ideanet.bank.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SavingTransaction {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
     private Date date;
     private String description;
     private String type;
     private String status;
     private Double amount;
     private BigDecimal availableBalance;
     
     @ManyToOne
     @JoinColumn(name = "savingAccount_fk")
     @JsonIgnore
     private SavingAccount savingAccount;
     
     public SavingTransaction() {
		// TODO Auto-generated constructor stub
	}

	public SavingTransaction(Date date, String description, String type, String status, Double amount,
			BigDecimal availableBalance, SavingAccount savingAccount) {
		super();
		this.date = date;
		this.description = description;
		this.type = type;
		this.status = status;
		this.amount = amount;
		this.availableBalance = availableBalance;
		this.savingAccount = savingAccount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public SavingAccount getSavingAccount() {
		return savingAccount;
	}

	public void setSavingAccount(SavingAccount savingAccount) {
		this.savingAccount = savingAccount;
	}

	
	
     
}
