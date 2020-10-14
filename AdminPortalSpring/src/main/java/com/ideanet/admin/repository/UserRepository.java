package com.ideanet.admin.repository;

import org.springframework.data.repository.CrudRepository;

import com.ideanet.admin.domain.User;

public interface UserRepository extends CrudRepository<User,Long>{
       
	User findByUsername(String username);
	
	User findByEmail(String email);
}
