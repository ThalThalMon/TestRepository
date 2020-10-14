package com.ideanet.bank.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ideanet.bank.domain.PrimaryAccount;
import com.ideanet.bank.domain.PrimaryTransaction;
import com.ideanet.bank.domain.SavingAccount;
import com.ideanet.bank.domain.SavingTransaction;
import com.ideanet.bank.domain.User;
import com.ideanet.bank.service.AccountService;
import com.ideanet.bank.service.TransactionService;
import com.ideanet.bank.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	  @Autowired
	  private UserService userService;
	  
	  @Autowired
	  private AccountService accountService;
	  
	  @Autowired
	  private TransactionService transactionService;

	  @RequestMapping("/primaryAccount")
	  public String primaryAccount(Model model,Principal principal) {
		  List<PrimaryTransaction> primaryTransactionList=transactionService.findPrimaryTransactionList(principal.getName());  
		  User user=userService.findByUsername(principal.getName());
		  PrimaryAccount primaryAccount=user.getPrimaryAccount();
		  model.addAttribute("primaryAccount",primaryAccount);
		  model.addAttribute("primaryTransactionList",primaryTransactionList);
		  return "primaryAccount";
	  }
	  
	  @RequestMapping("/savingsAccount")
	  public String savingsAccount(Model model,Principal principal) {
		  List<SavingTransaction> savingsTransactionList=transactionService.findSavingsTransactionList(principal.getName());
		  User user=userService.findByUsername(principal.getName());
		  SavingAccount savingsAccount=user.getSavingAccount();
		  model.addAttribute("savingsAccount",savingsAccount);
		  model.addAttribute("savingsTransactionList",savingsTransactionList);
		  return "savingsAccount";
	  }
	  
	  @RequestMapping("/deposit")
	  public String deposit(Model model) {
		   model.addAttribute("accountType","");
		   model.addAttribute("amount","");
		   
		   return "deposit";
	  }
	  
	  @RequestMapping(value = "/deposit",method = RequestMethod.POST)
	  public String createDeposit(@ModelAttribute("accountType") String accountType,@ModelAttribute("amount") Double amount,Principal principal) {
		 accountService.deposit(accountType, amount, principal);
		 
		 return "redirect:/userFront";
	  }
	  
	  
	  @RequestMapping("/withdraw")
	  public String withdraw(Model model) {
		   model.addAttribute("accountType","");
		   model.addAttribute("amount","");
		   
		   return "withdraw";
	  }
	  
	  @RequestMapping(value = "/withdraw",method = RequestMethod.POST)
	  public String createWithdraw(@ModelAttribute("accountType") String accountType,@ModelAttribute("amount") Double amount,Principal principal) {
		 accountService.withdraw(accountType, amount, principal);
		 
		 return "redirect:/userFront";
	  }
	 
}
