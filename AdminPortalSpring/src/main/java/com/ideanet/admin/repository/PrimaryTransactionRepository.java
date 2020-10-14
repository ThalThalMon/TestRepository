package com.ideanet.admin.repository;

import org.springframework.data.repository.CrudRepository;

import com.ideanet.admin.domain.PrimaryTransaction;

public interface PrimaryTransactionRepository extends CrudRepository<PrimaryTransaction,Long>{
   
}
