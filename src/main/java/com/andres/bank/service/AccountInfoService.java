package com.andres.bank.service;

import java.util.ArrayList;

import com.andres.bank.dao.AccountsDAO;
import com.andres.bank.dao.AccountsDAOImpl;
import com.andres.bank.exceptions.NoAccountFoundException;
import com.andres.bank.model.Account;

public class AccountInfoService {
	
	private AccountsDAO accountsDAO = new AccountsDAOImpl();

	public AccountInfoService() {
		super();
	}
	
	public ArrayList<Account> getActiveAccounts(String username) throws NoAccountFoundException
	{
		ArrayList<Account> userAccounts = new ArrayList<>();
		userAccounts = accountsDAO.getActiveAccounts(username);
		
		if(userAccounts.size() == 0)
		{
			throw new NoAccountFoundException("No active accounts found");
		}else
		{
			return userAccounts;
		}
		
		
	}
	

}
