package com.ideanet.bank.service.impl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideanet.bank.domain.PrimaryAccount;
import com.ideanet.bank.domain.PrimaryTransaction;
import com.ideanet.bank.domain.Recipient;
import com.ideanet.bank.domain.SavingAccount;
import com.ideanet.bank.domain.SavingTransaction;
import com.ideanet.bank.domain.User;
import com.ideanet.bank.repository.PrimaryAccountRepository;
import com.ideanet.bank.repository.PrimaryTransactionRepository;
import com.ideanet.bank.repository.RecipientRepository;
import com.ideanet.bank.repository.SavingAccountRepository;
import com.ideanet.bank.repository.SavingsTransactionRepository;
import com.ideanet.bank.service.TransactionService;
import com.ideanet.bank.service.UserService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PrimaryTransactionRepository primaryTransactionRepository;
	
	@Autowired
	private SavingsTransactionRepository savingsTransactionRepository;
	
	@Autowired
	private PrimaryAccountRepository primaryAccountRepository;
	
	@Autowired
	private SavingAccountRepository savingAccountRepository;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private RecipientRepository recipientRepository;
	
	@Override
	public List<PrimaryTransaction> findPrimaryTransactionList(String username) {
		User user = userService.findByUsername(username);
		List<PrimaryTransaction> primaryTransactionList = user.getPrimaryAccount().getPrimaryTransactions();
		return primaryTransactionList;
	}

	@Override
	public List<SavingTransaction> findSavingsTransactionList(String username) {
		User user = userService.findByUsername(username);
		List<SavingTransaction> savingsTransactionList = user.getSavingAccount().getSavingTransactions();
		return savingsTransactionList;
		
	}

	@Override
	public void savePrimaryTransaction(PrimaryTransaction primaryTransaction) {
		primaryTransactionRepository.save(primaryTransaction);
		
	}

	@Override
	public void saveSavingsTransaction(SavingTransaction savingTransaction) {
		savingsTransactionRepository.save(savingTransaction);
		
	}

	@Override
	public void betweenAccountsTransfer(String transferFrom, String transferTo, Double amount,
			PrimaryAccount primaryAccount, SavingAccount savingsAccount) {

		if (transferFrom.equalsIgnoreCase("Primary") && transferTo.equalsIgnoreCase("Savings")) {
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
			primaryAccountRepository.save(primaryAccount);
			savingAccountRepository.save(savingsAccount);

			Date date = new Date();
			PrimaryTransaction primaryTransaction = new PrimaryTransaction(date,"Between accounts Transfer from "+transferFrom+" to "+transferTo,
					"Account", "Finished", amount, primaryAccount.getAccountBalance(), primaryAccount);
			transactionService.savePrimaryTransaction(primaryTransaction);
		}

		if (transferFrom.equalsIgnoreCase("Savings") && transferTo.equalsIgnoreCase("Primary")) {
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
			primaryAccountRepository.save(primaryAccount);
			savingAccountRepository.save(savingsAccount);

			Date date = new Date();
			SavingTransaction savingsTransaction = new SavingTransaction(date,"Between accounts Transfer from "+transferFrom+" to "+transferTo,
					"Account","Finished", amount, savingsAccount.getAccountBalance(), savingsAccount);
			transactionService.saveSavingsTransaction(savingsTransaction);
		}

	}

	@Override
	public List<Recipient> findRecipientList(Principal principal) {
		String username=principal.getName();
		List<Recipient> recipientList=recipientRepository.findAll().stream()
				.filter(recipient -> username.equals(recipient.getUser().getUsername()) )
				.collect(Collectors.toList());
		return recipientList;
	}

	@Override
	public Recipient saveRecipient(Recipient recipient) {
		return recipientRepository.save(recipient);
	}

	@Override
	public Recipient findByRecipientName(String recipientName) {
		return recipientRepository.findByRecipientName(recipientName);
	}

	@Override
	public void deleteByRecipientName(String recipientName) {
	    recipientRepository.deleteByRecipientName(recipientName);
	}

	@Override
	public void toSomeoneElseTransfer(Recipient recipient, String accountType, Double amount,
			PrimaryAccount primaryAccount, SavingAccount savingsAccount) {
		if (accountType.equalsIgnoreCase("Primary")) {
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			primaryAccountRepository.save(primaryAccount);
			
			Date date=new Date();
			PrimaryTransaction primaryTransaction=new PrimaryTransaction(date,"Transfer To Recipient "+recipient.getRecipientName(),"Account","Finished",amount,primaryAccount.getAccountBalance(),primaryAccount);
			primaryTransactionRepository.save(primaryTransaction);
		}
		
		if (accountType.equalsIgnoreCase("Savings")) {
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			savingAccountRepository.save(savingsAccount);
			
			Date date = new Date();
			
			SavingTransaction savingTransaction=new SavingTransaction(date,"Transfer To Recipient "+recipient.getRecipientName(),"Account","Finished",amount,savingsAccount.getAccountBalance(),savingsAccount);
			savingsTransactionRepository.save(savingTransaction);
		}
		
	}

}
