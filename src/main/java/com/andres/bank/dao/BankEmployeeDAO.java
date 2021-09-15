package com.andres.bank.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface BankEmployeeDAO {
	
	public boolean checkEmployeeID(int employeeID, Connection con) throws SQLException;
	
	public boolean checkPassword(int employeeID, String password, Connection con) throws SQLException;
	
	public boolean checkAdmin(int employeeID, Connection con) throws SQLException;

	public void createEmployee(int employeeID, String password, boolean isAdmin, Connection con) throws SQLException;
}
