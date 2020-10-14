package com.ideanet.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ideanet.admin.domain.Appointment;
import com.ideanet.admin.domain.PrimaryTransaction;
import com.ideanet.admin.domain.SavingTransaction;
import com.ideanet.admin.domain.User;
import com.ideanet.admin.service.AppointmentService;
import com.ideanet.admin.service.TransactionService;
import com.ideanet.admin.service.UserService;

@Controller
public class AdminHomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
		
	}
	
	@RequestMapping("/adminHome")
	public String adminHome() {
		return "adminHome";
		
	}
	
	@RequestMapping("/user")
	public String userAccount(Model model) {
		List<User> userList=userService.findUserList();
		model.addAttribute("userList",userList);
		return "userAccount";
	}
	
	@RequestMapping(value = "/enableUser")
	public String enableUser(@RequestParam(value = "username",required = false) String username) {
		User user=userService.findByUsername(username);
		user.setEnabled(true);
		userService.saveUser(user);
		
		return "redirect:/user";
	}
	
	@RequestMapping(value = "/disableUser")
	public String disableUser(@RequestParam(value = "username",required = false) String username) {
		User user=userService.findByUsername(username);
		user.setEnabled(false);
		userService.saveUser(user);
		
		return "redirect:/user";
	}
	
	@RequestMapping("/appointment")
	public String appointment(Model model) {
		List<Appointment> appointmentList=appointmentService.findAll();
		model.addAttribute("appointmentList",appointmentList);
		return "appointment";
	}
	
	@RequestMapping("/comfirmedUser")
	public String comfirmedUser(@RequestParam("id") Long id) {
		appointmentService.comfirmAppointment(id);
		return "redirect:/appointment";
	}
	
	@RequestMapping("/primaryAccount")
	public String primaryAccount(@RequestParam("username") String username,Model model) {
		List<PrimaryTransaction> primaryTransactionList=transactionService.findPrimaryTransactionList(username);
		model.addAttribute("primaryTransactionList",primaryTransactionList);
		return "primaryAccount";
	}
	
	@RequestMapping("/savingsAccount")
	public String savingsAccount(@RequestParam("username") String username,Model model) {
		List<SavingTransaction> savingsTransactionList=transactionService.findSavingsTransactionList(username);
		model.addAttribute("savingsTransactionList",savingsTransactionList);
		return "savingsAccount";
	}

}
