package com.ideanet.admin.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ideanet.admin.domain.PrimaryTransaction;
import com.ideanet.admin.domain.SavingTransaction;
import com.ideanet.admin.domain.User;
import com.ideanet.admin.service.TransactionService;
import com.ideanet.admin.service.UserService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private UserService userService;
	
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


}
