package com.andres.bank.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.andres.bank.dao.AccountsDAO;
import com.andres.bank.dao.AccountsDAOImpl;
import com.andres.bank.exceptions.InvalidInputException;
import com.andres.bank.model.Account;
import com.andres.bank.util.ConnectionUtil;

public class AccountActionsService {

	public AccountsDAO accountsDAO = new AccountsDAOImpl();
	
	public void deposit(Account chosenAccount, String depositAmount) throws SQLException, InvalidInputException
	{
		double dep = Double.parseDouble(depositAmount);
		
		if(dep <= 0)
		{
			throw new InvalidInputException("Must enter an amount greater than 0.");
		}
		
		try(Connection con = ConnectionUtil.getConnection())
		{
			accountsDAO.deposit(chosenAccount.getAccountNumber(), dep, con);			
		}
		
	}
	
	public void withdraw(Account accountBeingAccessed, String withdrawAmount) throws SQLException, InvalidInputException
	{
		try(Connection con = ConnectionUtil.getConnection())
		{
			double withdraw = Double.parseDouble(withdrawAmount);
			
			double currentBalance = accountBeingAccessed.getBalance();
			
			if(currentBalance - Double.parseDouble(withdrawAmount) < 0)
			{
				throw new InvalidInputException("That withdrawal amount exceeds your current balance!");
			}else if(Double.parseDouble(withdrawAmount) <= 0)
			{
				throw new InvalidInputException("Withdrawal amount must be greater than 0.");				
			}
			
			accountsDAO.withdraw(accountBeingAccessed.getAccountNumber(), Double.parseDouble(withdrawAmount), con);
			
		}
		
	}
	
	public void updateAccountStatus(int applicationNumber, String decision) throws SQLException
	{
		try(Connection con = ConnectionUtil.getConnection())
		{
			accountsDAO.updateAccountStatus(applicationNumber, decision, con);
		}
	}
	
}
