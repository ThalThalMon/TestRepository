package com.ideanet.admin.service;

import java.util.List;

import com.ideanet.admin.domain.PrimaryTransaction;
import com.ideanet.admin.domain.SavingTransaction;

public interface TransactionService {
   
	List<PrimaryTransaction> findPrimaryTransactionList(String username);
	
	List<SavingTransaction> findSavingsTransactionList(String username);
	
}
