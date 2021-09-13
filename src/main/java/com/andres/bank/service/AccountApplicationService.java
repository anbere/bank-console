package com.andres.bank.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.andres.bank.dao.AccountsDAO;
import com.andres.bank.dao.AccountsDAOImpl;
import com.andres.bank.dao.BankUserDAO;
import com.andres.bank.dao.BankUserDAOImpl;
import com.andres.bank.exceptions.ProcessingApplicationException;
import com.andres.bank.util.ConnectionUtil;

public class AccountApplicationService {

	public AccountsDAO accountsDAO;
	public BankUserDAO bankUserDAO;
	
	public AccountApplicationService()
	{
		this.accountsDAO = new AccountsDAOImpl();
		this.bankUserDAO = new BankUserDAOImpl();
	}
	
	public boolean applyForNewAccount(String accountType, double initialBalance, String currentUser) throws SQLException, ProcessingApplicationException
	{
		try(Connection con = ConnectionUtil.getConnection())
		{
			return accountsDAO.applyForNewAccount(accountType, initialBalance, currentUser, con);
		}
	}
	
}
