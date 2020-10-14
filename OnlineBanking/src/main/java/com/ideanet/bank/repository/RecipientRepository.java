package com.ideanet.bank.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ideanet.bank.domain.Recipient;

public interface RecipientRepository extends CrudRepository<Recipient,Long> {

	    List<Recipient> findAll();
	
	    Recipient findByRecipientName(String recipientName);
	    
	    void deleteByRecipientName(String recipientName);
	
}
