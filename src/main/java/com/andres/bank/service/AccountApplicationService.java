package com.andres.bank.service;

import com.andres.bank.dao.AccountsDAO;
import com.andres.bank.dao.AccountsDAOImpl;
import com.andres.bank.dao.BankUserDAO;
import com.andres.bank.dao.BankUserDAOImpl;
import com.andres.bank.exceptions.ProcessingApplicationException;

public class AccountApplicationService {

	public AccountsDAO accountsDAO;
	public BankUserDAO bankUserDAO;
	
	public AccountApplicationService()
	{
		this.accountsDAO = new AccountsDAOImpl();
		this.bankUserDAO = new BankUserDAOImpl();
	}
	
	public boolean applyForNewAccount(String accountType, double initialBalance, String currentUser) throws ProcessingApplicationException
	{
		if(initialBalance < 500)
		{
			System.out.println("Must apply with initial deposit of at least $500. Try again.");
			return false;
		}else if(initialBalance > 500000)
		{
			System.out.println("Must apply with initial deposit no more than $500,000. Try again.");
			return false;
		}
		return accountsDAO.applyForNewAccount(accountType, initialBalance, currentUser);
	}
	
}
