package com.ideanet.bank.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ideanet.bank.domain.PrimaryAccount;
import com.ideanet.bank.domain.SavingAccount;
import com.ideanet.bank.domain.User;
import com.ideanet.bank.domain.security.UserRole;
import com.ideanet.bank.repository.RoleRepository;
import com.ideanet.bank.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";	
	}
	
	@RequestMapping("/signup")
	public String signUp(Model model) {
		User user=new User();
		model.addAttribute("user",user);
		return "signup";
	}
	
	@RequestMapping(value = "/signup",method = RequestMethod.POST)
	public String signUp(@ModelAttribute("user") User user,Model model) {
		
		if (userService.checkUserExists(user.getUsername(),user.getEmail())) {
			if (userService.checkUsernameExists(user.getUsername())) {
				model.addAttribute("usernameExists",true);
			}
			
			if (userService.checkEmailExists(user.getEmail())) {
				model.addAttribute("emailExists",true);
			}
			
			return "signup";
			
		}else {
			Set<UserRole> userRoles=new HashSet<UserRole>();
			userRoles.add(new UserRole(user,roleRepository.findByName("ROLE_USER")));
			userService.createUser(user, userRoles);
			return "redirect:/";
		}
	}
	
	@RequestMapping("/userFront")
	public String userFront(Principal principal,Model model) {
		User user=userService.findByUsername(principal.getName());
		PrimaryAccount primaryAccount=user.getPrimaryAccount();
		SavingAccount savingAccount=user.getSavingAccount();
		
		model.addAttribute("primaryAccount",primaryAccount);
		model.addAttribute("savingAccount",savingAccount);
		return "userFront";
		
	}

	
}
