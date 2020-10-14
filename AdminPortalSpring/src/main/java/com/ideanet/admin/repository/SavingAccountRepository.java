package com.ideanet.admin.repository;

import org.springframework.data.repository.CrudRepository;

import com.ideanet.admin.domain.SavingAccount;

public interface SavingAccountRepository extends CrudRepository<SavingAccount,Long>{

	SavingAccount findByAccountNumber(Integer accountNumber);
}
