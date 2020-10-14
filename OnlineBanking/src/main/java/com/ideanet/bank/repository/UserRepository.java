package com.ideanet.bank.repository;

import org.springframework.data.repository.CrudRepository;

import com.ideanet.bank.domain.User;

public interface UserRepository extends CrudRepository<User,Long>{
       
	User findByUsername(String username);
	
	User findByEmail(String email);
}
