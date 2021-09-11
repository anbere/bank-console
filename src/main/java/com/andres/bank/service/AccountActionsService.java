package com.andres.bank.service;

import com.andres.bank.dao.AccountsDAO;
import com.andres.bank.dao.AccountsDAOImpl;
import com.andres.bank.exceptions.BlankEntryException;
import com.andres.bank.exceptions.InvalidInputException;
import com.andres.bank.model.Account;

public class AccountActionsService {

	public AccountsDAO accountsDAO = new AccountsDAOImpl();
	
	public void deposit(Account accountBeingAccessed, double depositAmount) throws BlankEntryException, InvalidInputException
	{
		String deposit = String.valueOf(depositAmount);
		String accKey = accountBeingAccessed.getAccountOwner() + accountBeingAccessed.getAccountType();
		
		if (deposit.matches("^\\s*$") || deposit == null) {
			throw new BlankEntryException("Nothing was entered");
		}

		if (depositAmount < 0) {
			throw new InvalidInputException("Deposit amount must be greater than 0");
		}

		accountsDAO.deposit(depositAmount, accKey);
		
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
