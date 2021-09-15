package com.andres.bank.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.andres.bank.dao.BankEmployeeDAO;
import com.andres.bank.dao.BankEmployeeDAOImpl;
import com.andres.bank.exceptions.IncorrectPasswordException;
import com.andres.bank.exceptions.InvalidInputException;
import com.andres.bank.exceptions.UserNotFoundException;
import com.andres.bank.util.ConnectionUtil;

public class EmployeeService {

	public BankEmployeeDAO bankEmployeeDAO = new BankEmployeeDAOImpl();

	public boolean checkEmployeeID(int employeeID) throws UserNotFoundException, SQLException, InvalidInputException {
		
		try (Connection con = ConnectionUtil.getConnection()) {

			if (employeeID > 999999)
			{
				throw new InvalidInputException("Not a valid Employee ID format.");
			}else if(bankEmployeeDAO.checkEmployeeID(employeeID, con))
			{
				return true;
			}else
			{
				throw new UserNotFoundException("Employee not found.");
			}

		}

	}
	
	public boolean checkPassword(int employeeID, String password) throws SQLException, IncorrectPasswordException
	{
		try (Connection con = ConnectionUtil.getConnection()) {

			if(bankEmployeeDAO.checkPassword(employeeID, password, con))
			{
				return true;
			}else
			{
				throw new IncorrectPasswordException("Incorrect employee password entered.");
			}

		}
	}
	
	public boolean checkAdmin(int employeeID) throws SQLException
	{
		try (Connection con = ConnectionUtil.getConnection())
		{
			return bankEmployeeDAO.checkAdmin(employeeID, con);
		}
	}
	
}
