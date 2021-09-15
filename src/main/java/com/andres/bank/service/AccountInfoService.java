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
			userAccounts = accountsDAO.getActiveAccounts(username, con);
		}
		
		
		if(userAccounts.size() == 0)
		{
			throw new NoAccountFoundException("No active accounts found for: " + username);
		}else
		{
			return userAccounts;
		}
	}
	
	public ArrayList<Account> getPendingAccounts(String username) throws NoAccountFoundException, SQLException
	{
		ArrayList<Account> userAccounts = new ArrayList<>();
		

		try(Connection con = ConnectionUtil.getConnection())
		{
			userAccounts = accountsDAO.getPendingAccounts(username, con);
		}
		
		
		if(userAccounts.size() == 0)
		{
			throw new NoAccountFoundException("No pending accounts found for: " + username);
		}else
		{
			return userAccounts;
		}
	}
	
	public Account getApplicationByNumber(String applicationNumber) throws SQLException, NoAccountFoundException
	{
		Account account = null;
		
		try(Connection con = ConnectionUtil.getConnection())
		{
			account = accountsDAO.getApplicationByNumber(Integer.parseInt(applicationNumber), con);
		}
		
		if(account == null)
		{
			throw new NoAccountFoundException("No account found with application number: " + applicationNumber);
		}
		
		return account;
	}
	
	public Account getAccountByNumber(String accountNumber) throws SQLException, NoAccountFoundException
	{
		Account account = null;
		
		try(Connection con = ConnectionUtil.getConnection())
		{
			account = accountsDAO.getAccountByNumber(Integer.parseInt(accountNumber), con);
		}
		
		if(account == null) {
			throw new NoAccountFoundException("No account found with account number: " + accountNumber);
		}	
		
		return account;
	}

	public ArrayList<Account> getAccountsByUsername(String username) throws SQLException, NoAccountFoundException {
		
		ArrayList<Account> accounts = new ArrayList<>();
		
		try(Connection con = ConnectionUtil.getConnection())
		{
			accounts = accountsDAO.getAccountsByUsername(username, con);
		}
		
		if(accounts.size() == 0) {
			throw new NoAccountFoundException("No accounts found under user: " + username);
		}
		else {
			return accounts;
		}
	}
	
}
