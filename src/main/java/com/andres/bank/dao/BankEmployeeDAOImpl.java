package com.andres.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankEmployeeDAOImpl implements BankEmployeeDAO
{

	@Override
	public boolean checkEmployeeID(int employeeID, Connection con) throws SQLException
	{
		int returnedID = -1;
		String sql = "SELECT employee_id FROM bank_console.employees WHERE employee_id = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, employeeID);

		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			returnedID = rs.getInt("employee_id");
		}
		
		if(returnedID != -1)
		{
			return true;
		}
		return false;
	}
	
	public boolean checkPassword(int employeeID, String password, Connection con) throws SQLException
	{
		String sql = "SELECT employee_id, password FROM bank_console.employees WHERE (employee_id = ? AND password = ?);";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, employeeID);
		ps.setString(2, password);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			int match = rs.getInt("employee_id");

			return match == employeeID;
		}

		return false;

	}

	@Override
	public boolean checkAdmin(int employeeID, Connection con) throws SQLException {
		
		boolean isAdmin = false;
		String sql = "SELECT employee_id, is_admin FROM bank_console.employees WHERE employee_id = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, employeeID);

		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			isAdmin = rs.getBoolean("is_admin");
		}
		
		return isAdmin;
	}

}
