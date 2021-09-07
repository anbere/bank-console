package com.andres.bank.service;

import com.andres.bank.dao.BankUserDAO;
import com.andres.bank.dao.BankUserDAOImpl;
import com.andres.bank.exceptions.BlankEntryException;
import com.andres.bank.exceptions.IncorrectPasswordException;
import com.andres.bank.exceptions.UserNotFoundException;

public class UserService {
	
	public BankUserDAO bankUserDAO;
	
	public UserService()
	{
		this.bankUserDAO = new BankUserDAOImpl();
	}
	
	public boolean hasCorrectUsername(String username) throws BlankEntryException, UserNotFoundException
	{
		if(username.matches("^\\s*$") || username == null)
		{
			throw new BlankEntryException("Nothing was entered");
		} else if(bankUserDAO.hasCorrectUsername(username))
		{
			return true;
		} else
		{
			throw new UserNotFoundException("Username not found, try again.");
		}

	}
	
	public boolean hasCorrectPassword(String username, String password) throws BlankEntryException, IncorrectPasswordException
	{
		if(password.matches("^\\s*$") || username == null)
		{
			throw new BlankEntryException("Nothing was entered");
		} else if(bankUserDAO.hasCorrectPassword(username, password))
		{
			return true;
		} else
		{
			throw new IncorrectPasswordException("WRONG PASSWORD");
		}
	}
}
