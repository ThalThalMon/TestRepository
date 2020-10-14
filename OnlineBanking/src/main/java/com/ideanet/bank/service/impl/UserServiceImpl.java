package com.ideanet.bank.service.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ideanet.bank.domain.User;
import com.ideanet.bank.domain.security.UserRole;
import com.ideanet.bank.repository.RoleRepository;
import com.ideanet.bank.repository.UserRepository;
import com.ideanet.bank.service.AccountService;
import com.ideanet.bank.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	private static final Logger LOG=LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AccountService accountService;

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Boolean checkUserExists(String username, String email) {
		if (checkUsernameExists(username) || checkEmailExists(email)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean checkUsernameExists(String username) {
		if (findByUsername(username) != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean checkEmailExists(String email) {
		if (findByEmail(email) != null) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public User createUser(User user, Set<UserRole> userRoles) {
		User localUser=userRepository.findByUsername(user.getUsername());
		
		if (localUser != null) {
			LOG.info("User with Username {} already exist.Nothing will be done.",user.getUsername());
		}else {
			
			String encryptedPassword=passwordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			
			for(UserRole ur:userRoles) {
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			
			user.setPrimaryAccount(accountService.createPrimaryAccount());
			user.setSavingAccount(accountService.createSavingAccount());
			
			localUser=userRepository.save(user);
			
		}
		return localUser;
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

}
