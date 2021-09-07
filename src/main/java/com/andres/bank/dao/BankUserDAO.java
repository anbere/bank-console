package com.andres.bank.dao;

import com.andres.bank.exceptions.UsernameTakenException;
import com.andres.bank.model.BankUser;

public interface BankUserDAO {

	
	public boolean hasCorrectUsername(String username);

	public boolean hasCorrectPassword(String username, String password);

	public void createNewUser(String username, String password) throws UsernameTakenException;
	
}
