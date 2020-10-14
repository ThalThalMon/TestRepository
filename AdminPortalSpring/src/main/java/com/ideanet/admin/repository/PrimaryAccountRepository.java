package com.ideanet.admin.repository;

import org.springframework.data.repository.CrudRepository;

import com.ideanet.admin.domain.PrimaryAccount;

public interface PrimaryAccountRepository extends CrudRepository<PrimaryAccount,Long>{

	PrimaryAccount findByAccountNumber(Integer accountNumber);
}
