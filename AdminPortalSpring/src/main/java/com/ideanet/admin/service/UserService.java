package com.ideanet.admin.service;

import java.util.List;
import java.util.Set;
import com.ideanet.admin.domain.User;
import com.ideanet.admin.domain.security.UserRole;


public interface UserService {

	User findByUsername(String username);
	
	User findByEmail(String email);
	
	List<User> findUserList();
	
	User createUser(User user,Set<UserRole> userRoles);
	
	User saveUser(User user);
	
	void enableUser(String username);
	
	void disableUser(String username);
	
}
