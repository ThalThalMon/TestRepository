package com.ideanet.admin.service.serviceImpl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ideanet.admin.domain.User;
import com.ideanet.admin.domain.security.UserRole;
import com.ideanet.admin.repository.RoleRepository;
import com.ideanet.admin.repository.UserRepository;
import com.ideanet.admin.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	private static final Logger LOG=LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	
	@Override
	public List<User> findUserList() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public void enableUser(String username) {
    User user=userRepository.findByUsername(username);
    user.setEnabled(true);
    userRepository.save(user);
		
	}

	@Override
	public void disableUser(String username) {
		User user = userRepository.findByUsername(username);
		user.setEnabled(false);
		userRepository.save(user);
		
	}
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) {
		User localUser=userRepository.findByUsername(user.getUsername());
			
			String encryptedPassword=passwordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			
			for(UserRole ur:userRoles) {
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			
			localUser=userRepository.save(user);
			
		
	         return localUser;
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

}
