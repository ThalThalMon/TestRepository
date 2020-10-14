package com.ideanet.bank.service;

import java.security.Principal;

import com.ideanet.bank.domain.PrimaryAccount;
import com.ideanet.bank.domain.SavingAccount;

public interface AccountService {

	PrimaryAccount createPrimaryAccount();
	
	SavingAccount createSavingAccount();
	
	void deposit(String accountType,Double amount,Principal principal);
	
	void withdraw(String accountType,Double amount,Principal principal);
}
