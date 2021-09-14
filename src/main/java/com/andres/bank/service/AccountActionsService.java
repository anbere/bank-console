package com.andres.bank.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.andres.bank.dao.AccountsDAO;
import com.andres.bank.dao.AccountsDAOImpl;
import com.andres.bank.exceptions.BlankEntryException;
import com.andres.bank.exceptions.InvalidInputException;
import com.andres.bank.model.Account;
import com.andres.bank.util.ConnectionUtil;

public class AccountActionsService {

	public AccountsDAO accountsDAO = new AccountsDAOImpl();
	
	public void deposit(Account chosenAccount, double depositAmount) throws SQLException
	{
		
		try(Connection con = ConnectionUtil.getConnection())
		{
			accountsDAO.deposit(chosenAccount.getAccountNumber(), depositAmount, con);			
		}
		
	}
	
	public void withdraw(Account accountBeingAccessed, double withdrawAmount) throws BlankEntryException, InvalidInputException
	{
		String withdraw = String.valueOf(withdrawAmount);
		String accKey = accountBeingAccessed.getAccountOwner() + accountBeingAccessed.getAccountType();
		
		if (withdraw.matches("^\\s*$") || withdraw == null)
		{
			throw new BlankEntryException("Nothing was entered");
		}
		
		if(withdrawAmount > accountBeingAccessed.getBalance())
		{
			throw new InvalidInputException("Insufficient funds, withdraw a smaller amount.");
		}
		
		accountsDAO.withdraw(withdrawAmount, accKey);
		
	}
	
}
