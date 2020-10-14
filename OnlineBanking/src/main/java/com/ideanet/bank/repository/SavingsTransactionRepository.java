package com.ideanet.bank.repository;

import org.springframework.data.repository.CrudRepository;

import com.ideanet.bank.domain.SavingTransaction;

public interface SavingsTransactionRepository extends CrudRepository<SavingTransaction,Long>{

}
