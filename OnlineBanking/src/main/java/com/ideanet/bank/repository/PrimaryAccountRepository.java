package com.ideanet.bank.repository;

import org.springframework.data.repository.CrudRepository;

import com.ideanet.bank.domain.PrimaryAccount;

public interface PrimaryAccountRepository extends CrudRepository<PrimaryAccount,Long>{

	PrimaryAccount findByAccountNumber(Integer accountNumber);
}
