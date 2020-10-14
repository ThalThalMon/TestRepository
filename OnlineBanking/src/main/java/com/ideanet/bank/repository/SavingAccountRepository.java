package com.ideanet.bank.repository;

import org.springframework.data.repository.CrudRepository;

import com.ideanet.bank.domain.SavingAccount;

public interface SavingAccountRepository extends CrudRepository<SavingAccount,Long>{

	SavingAccount findByAccountNumber(Integer accountNumber);
}
