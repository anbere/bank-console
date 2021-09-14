package com.andres.bank.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.andres.bank.dao.BankUserDAO;
import com.andres.bank.dao.BankUserDAOImpl;
import com.andres.bank.exceptions.BlankEntryException;
import com.andres.bank.exceptions.IncorrectPasswordException;
import com.andres.bank.exceptions.UserNotFoundException;
import com.andres.bank.util.ConnectionUtil;

public class UserService {

	public BankUserDAO bankUserDAO;

	public UserService() {
		this.bankUserDAO = new BankUserDAOImpl();
	}

	public boolean hasCorrectUsername(String username) throws BlankEntryException, UserNotFoundException, SQLException {
		try (Connection con = ConnectionUtil.getConnection()) {

			if (username.matches("^\\s*$") || username == null) {
				throw new BlankEntryException("Nothing was entered");
			} else if (bankUserDAO.hasCorrectUsername(username, con)) {
				return true;
			} else
				throw new UserNotFoundException("Username not found. Try again.");
		}

	}

	public boolean hasCorrectPassword(String username, String password)
			throws BlankEntryException, IncorrectPasswordException, SQLException {
		try (Connection con = ConnectionUtil.getConnection()) {
			if (password.matches("^\\s*$") || password == null) {
				throw new BlankEntryException("Nothing was entered");
			} else if (bankUserDAO.hasCorrectPassword(username, password, con)) {
				return true;
			} else
				throw new IncorrectPasswordException("WRONG PASSWORD");

		}
	}
}
