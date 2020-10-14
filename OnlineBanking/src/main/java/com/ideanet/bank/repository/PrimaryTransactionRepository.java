package com.ideanet.bank.repository;

import org.springframework.data.repository.CrudRepository;

import com.ideanet.bank.domain.PrimaryTransaction;

public interface PrimaryTransactionRepository extends CrudRepository<PrimaryTransaction,Long>{
   
}
