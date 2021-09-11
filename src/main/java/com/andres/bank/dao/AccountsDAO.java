package com.andres.bank.dao;

import java.util.ArrayList;

import com.andres.bank.exceptions.ProcessingApplicationException;
import com.andres.bank.model.Account;

public interface AccountsDAO {

	public boolean applyForNewAccount(String accountType, double initialBalance, String username) throws ProcessingApplicationException;

	public ArrayList<Account> getActiveAccounts(String username);

	public void deposit(double depositAmount, String accKey);
	
	public void withdraw(double depositAmount, String accKey);

}
