package com.ideanet.bank.service;

import java.util.List;
import java.util.Set;

import com.ideanet.bank.domain.User;
import com.ideanet.bank.domain.security.UserRole;

public interface UserService {

	User findByUsername(String username);
	
	User findByEmail(String email);
	
	Boolean checkUserExists(String username,String email);
	
	Boolean checkUsernameExists(String username);
	
	Boolean checkEmailExists(String email);
	
	User saveUser(User user);
	
	User createUser(User user,Set<UserRole> userRoles);
	
	List<User> findUserList();
	
	void enableUser(String username);
	
	void disableUser(String username);
	
}
