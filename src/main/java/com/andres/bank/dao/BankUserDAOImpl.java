package com.andres.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BankUserDAOImpl implements BankUserDAO {

	@Override
	public boolean hasCorrectUsername(String username, Connection con) throws SQLException
	{
		
		String sql = "SELECT username FROM bank_console.bank_user WHERE username = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);

		ResultSet rs = ps.executeQuery();
		String returnedUser = null;
		
		if (rs.next()) {
			returnedUser = rs.getString("username");
		}
		if (returnedUser != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean hasCorrectPassword(String username, String password, Connection con) throws SQLException {

		String sql = "SELECT username, user_pass FROM bank_console.bank_user WHERE (username = ? AND user_pass = ?);";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			String match = rs.getString("username");

			return match.equals(username);
		}

		return false;

	}

	@Override
	public void createNewUser(String username, String password, Connection con) throws SQLException {

		String sql = "INSERT INTO bank_console.bank_user(username, user_pass) VALUES (?, ?);";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);

		int check = ps.executeUpdate();

		if (check != 1) {
			throw new SQLException("Did not execute SQL Statement");
		}

	}

	@Override
	public ArrayList<String> getAllUsernames(Connection con) throws SQLException {
		
		ArrayList<String> usernames = new ArrayList<>();
		
		String sql = "SELECT username FROM bank_console.bank_user;";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) 
		{
			usernames.add(rs.getString("username"));
		}

		return usernames;
	}

}
