package com.andres.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.andres.bank.exceptions.UsernameTakenException;
import com.andres.bank.model.BankUser;
import com.andres.bank.util.BankUserUtil;

public class BankUserDAOImpl implements BankUserDAO {

	private final String filepath = "./files/bank_user.txt";

	@Deprecated
	public boolean hasCorrectUsername1(String username) {
		HashMap<String, BankUser> currentMap = BankUserUtil.currentBankUserMap();
		for (String key : currentMap.keySet()) {
			if (key.equals(username)) {
				return true;
			}

		}

		return false;

	}
	
	@Override
	public boolean hasCorrectUsername(String username, Connection con) throws SQLException
	{
		
		String sql = "SELECT username FROM bank_console.bank_user WHERE username = ?";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);

		ResultSet rs = pstmt.executeQuery();
		String returnedUser = null;
		
		if (rs.next()) {
			returnedUser = rs.getString("username");
		}
		if (returnedUser != null) {
			return true;
		}

		return false;
	}

	@Deprecated
	public boolean hasCorrectPassword1(String username, String password) {
		HashMap<String, BankUser> currentMap = BankUserUtil.currentBankUserMap();

		if (password.equals(currentMap.get(username).getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean hasCorrectPassword(String username, String password, Connection con) throws SQLException {

		String sql = "SELECT username, user_pass FROM bank_console.bank_user WHERE (username = ? AND user_pass = ?);";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setString(2, password);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			String un = rs.getString("username");

			return un.equals(username);
		}

		return false;

	}

	@Deprecated
	public void createNewUser1(String username, String password) throws UsernameTakenException {

		HashMap<String, BankUser> currentMap = new HashMap<>();
		BankUser user = new BankUser(username, password);

		// Reads in currentMap from file, if empty returns empty HashMap
		currentMap = BankUserUtil.currentBankUserMap();

		// Check if username is already in currentMap
		for (String key : currentMap.keySet()) {
			if (key.equals(username)) {
				throw new UsernameTakenException();
			}
		}

		// Adds new user to currentMap
		currentMap.put(user.getName(), user);

		// Writes new currentMap to file
		BankUserUtil.writeToBankUserList(currentMap);
	}

	@Override
	public void createNewUser(String username, String password, Connection con) throws SQLException {

		String sql = "INSERT INTO bank_console.bank_user(username, user_pass) VALUES (?, ?);";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setString(2, password);

		int check = pstmt.executeUpdate();

		if (check != 1) {
			throw new SQLException("Did not execute SQL Statement");
		}

	}

}
