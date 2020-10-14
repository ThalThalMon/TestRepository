package com.ideanet.bank.service;

import java.security.Principal;
import java.util.List;

import com.ideanet.bank.domain.PrimaryAccount;
import com.ideanet.bank.domain.PrimaryTransaction;
import com.ideanet.bank.domain.Recipient;
import com.ideanet.bank.domain.SavingAccount;
import com.ideanet.bank.domain.SavingTransaction;

public interface TransactionService {
   
	List<PrimaryTransaction> findPrimaryTransactionList(String username);
	
	List<SavingTransaction> findSavingsTransactionList(String username);
	
	void savePrimaryTransaction(PrimaryTransaction primaryTransaction);
	
	void saveSavingsTransaction(SavingTransaction savingsTransaction);
	
	void betweenAccountsTransfer(String transferFrom,String transferTo,Double amount,PrimaryAccount primaryAccount,SavingAccount savingsAccount);
	
	List<Recipient> findRecipientList(Principal principal);
	
	Recipient saveRecipient(Recipient recipient);
	
	Recipient findByRecipientName(String recipientName);
	
	void deleteByRecipientName(String recipientName);
	
	void toSomeoneElseTransfer(Recipient recipient,String accountType,Double amount,PrimaryAccount primaryAccount,SavingAccount savingsAccount);
}
