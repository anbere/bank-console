package com.andres.bank.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.andres.bank.dao.AccountsDAO;
import com.andres.bank.dao.AccountsDAOImpl;
import com.andres.bank.exceptions.NoAccountFoundException;
import com.andres.bank.model.Account;
import com.andres.bank.util.ConnectionUtil;

public class AccountInfoService {
	
	private AccountsDAO accountsDAO = new AccountsDAOImpl();

	public AccountInfoService() {
		super();
	}
	
	public ArrayList<Account> getActiveAccounts(String username) throws NoAccountFoundException, SQLException
	{
		ArrayList<Account> userAccounts = new ArrayList<>();
		

		try(Connection con = ConnectionUtil.getConnection())
		{
			userAccounts = accountsDAO.getPendingAccounts(username, con);
		}
		
		
		if(userAccounts.size() == 0)
		{
			throw new NoAccountFoundException("No active accounts found");
		}else
		{
			return userAccounts;
		}
		
		
	}
	

}
