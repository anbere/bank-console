package com.andres.bank.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.andres.bank.dao.BankUserDAO;
import com.andres.bank.dao.BankUserDAOImpl;
import com.andres.bank.exceptions.InvalidPasswordException;
import com.andres.bank.exceptions.InvalidUsernameException;
import com.andres.bank.exceptions.UsernameTakenException;
import com.andres.bank.util.ConnectionUtil;

public class UserCreatorService {
	
	public BankUserDAO bankUserDAO;
	
	public UserCreatorService()
	{
		this.bankUserDAO = new BankUserDAOImpl();
	}
	
	public void createUser(String username, String password) throws UsernameTakenException, InvalidUsernameException, InvalidPasswordException
	{

		try(Connection con = ConnectionUtil.getConnection())
		{
			if(username.matches("^\\s*$") || username == null)
			{
//				System.out.println("Invalid username, no whitespace and empty username not allowed.");
				throw new InvalidUsernameException();
			} else if (password.matches("^\\s*$") || password == null) {
//				System.out.println("Invalid password, no whitespace and empty password not allowed.");
				throw new InvalidPasswordException();
			} else {
				bankUserDAO.createNewUser(username, password, con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
}
