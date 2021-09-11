package com.andres.bank.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface BankUserDAO {

	
	public boolean hasCorrectUsername(String username, Connection con) throws SQLException;

	public boolean hasCorrectPassword(String username, String password, Connection con) throws SQLException;

	public void createNewUser(String username, String password, Connection con) throws SQLException;
	
}
