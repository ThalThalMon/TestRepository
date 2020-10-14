package com.ideanet.bank.service.impl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideanet.bank.domain.PrimaryAccount;
import com.ideanet.bank.domain.PrimaryTransaction;
import com.ideanet.bank.domain.SavingAccount;
import com.ideanet.bank.domain.SavingTransaction;
import com.ideanet.bank.domain.User;
import com.ideanet.bank.repository.PrimaryAccountRepository;
import com.ideanet.bank.repository.SavingAccountRepository;
import com.ideanet.bank.service.AccountService;
import com.ideanet.bank.service.TransactionService;
import com.ideanet.bank.service.UserService;

@Service
public class AccountServiceImpl implements AccountService{
	
	private static Integer nextAccountNumber = 11223165;

	@Autowired
	private PrimaryAccountRepository primaryAccountRepository;
	
	@Autowired
	private SavingAccountRepository savingAccountRepository; 
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Override
	public PrimaryAccount createPrimaryAccount() {
		PrimaryAccount primaryAccount=new PrimaryAccount();
		primaryAccount.setAccountBalance(new BigDecimal(0.0));
		
		primaryAccount.setAccountNumber(accountGen());
		
		primaryAccountRepository.save(primaryAccount);
		return primaryAccountRepository.findByAccountNumber(primaryAccount.getAccountNumber());
	}

	private Integer accountGen() {
		return ++nextAccountNumber;
	}

	@Override
	public SavingAccount createSavingAccount() {
		SavingAccount savingAccount=new SavingAccount();
		savingAccount.setAccountBalance(new BigDecimal(0.0));
		savingAccount.setAccountNumber(accountGen());
		
		savingAccountRepository.save(savingAccount);
		return savingAccountRepository.findByAccountNumber(savingAccount.getAccountNumber());
	}

	@Override
	public void deposit(String accountType, Double amount, Principal principal) {
		User user=userService.findByUsername(principal.getName());
		
		if (accountType.equalsIgnoreCase("Primary")) {
			PrimaryAccount primaryAccount=user.getPrimaryAccount();
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
			primaryAccountRepository.save(primaryAccount);
			
			Date date=new Date();
			PrimaryTransaction primaryTransaction=new PrimaryTransaction(date,"Deposit to Primary Account","Account","Finished",amount,primaryAccount.getAccountBalance(),primaryAccount);
			transactionService.savePrimaryTransaction(primaryTransaction);
		}
		
		if (accountType.equalsIgnoreCase("Savings")) {
			SavingAccount savingsAccount=user.getSavingAccount();
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
			savingAccountRepository.save(savingsAccount);
			
			Date date=new Date();
			SavingTransaction savingsTransaction=new SavingTransaction(date,"Deposit to Savings Account","Account","Finished",amount,savingsAccount.getAccountBalance(),savingsAccount);
			transactionService.saveSavingsTransaction(savingsTransaction);
		}
		
	}

	@Override
	public void withdraw(String accountType, Double amount, Principal principal) {
        User user=userService.findByUsername(principal.getName());
		
		if (accountType.equalsIgnoreCase("Primary")) {
			PrimaryAccount primaryAccount=user.getPrimaryAccount();
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			primaryAccountRepository.save(primaryAccount);
			
			Date date=new Date();
			PrimaryTransaction primaryTransaction=new PrimaryTransaction(date,"Withdraw from Primary Account","Account","Finished",amount,primaryAccount.getAccountBalance(),primaryAccount);
			transactionService.savePrimaryTransaction(primaryTransaction);
		}
		
		if (accountType.equalsIgnoreCase("Savings")) {
			SavingAccount savingsAccount=user.getSavingAccount();
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			savingAccountRepository.save(savingsAccount);
			
			Date date=new Date();
			SavingTransaction savingsTransaction=new SavingTransaction(date,"Withdraw from Savings Account","Account","Finished",amount,savingsAccount.getAccountBalance(),savingsAccount);
			transactionService.saveSavingsTransaction(savingsTransaction);
		}    
		
	}

}
