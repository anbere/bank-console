package com.andres.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.andres.bank.exceptions.ProcessingApplicationException;
import com.andres.bank.model.Account;
import com.andres.bank.util.AccountsUtil;

public class AccountsDAOImpl implements AccountsDAO {

	@Override
	public boolean applyForNewAccount(String accountType, double initialBalance, String username, Connection con)
			throws ProcessingApplicationException, SQLException {

		String sql = "INSERT INTO bank_console.pending_accounts(account_owner,account_type,initial_balance,status) VALUES(?,?,?,'PENDING');";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, username);
		pstmt.setString(2, accountType);
		pstmt.setDouble(3, initialBalance);

		int count = pstmt.executeUpdate();

		if (count != 1) {
			return false;
		}

		return true;

	}

	@Override
	public ArrayList<Account> getActiveAccounts(String username, Connection con) throws SQLException {

		ArrayList<Account> accounts = new ArrayList<>();

		String sql = "SELECT * FROM bank_console.active_accounts WHERE account_owner = ?;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);

		ResultSet rs = pstmt.executeQuery();

		String status = "ACTIVE";

		while (rs.next()) {
			int accountNumber = rs.getInt("account_number");
			String accountOwner = rs.getString("account_owner");
			String accountType = rs.getString("account_type");
			double balance = rs.getDouble("balance");

			accounts.add(new Account(status, accountNumber, accountType, balance, accountOwner));
		}

		return accounts;
	}

	public ArrayList<Account> getPendingAccounts(String username, Connection con) throws SQLException {
		
		ArrayList<Account> accounts = new ArrayList<>();

		String sql = "SELECT * FROM bank_console.pending_accounts WHERE account_owner = ?;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);

		ResultSet rs = pstmt.executeQuery();
		
		String status = "PENDING";
		
		while (rs.next()) {
			String accountOwner = rs.getString("account_owner");
			String accountType = rs.getString("account_type");
			double balance = rs.getDouble("initial_balance");

			accounts.add(new Account(status, accountType, balance, accountOwner));
		}

		return accounts;
	}

	@Override
	public void deposit(double depositAmount, String accKey) {
		HashMap<String, Account> currentMap = AccountsUtil.currentAccountsMap();
		Account updated = currentMap.get(accKey);

		updated.setBalance(updated.getBalance() + depositAmount);
//		currentMap.remove(accKey);
		currentMap.put(accKey, updated);

		AccountsUtil.writeToAccountList(currentMap);
	}

	public void withdraw(double withdrawAmount, String accKey) {
		HashMap<String, Account> currentMap = AccountsUtil.currentAccountsMap();
		Account updated = currentMap.get(accKey);

		updated.setBalance(updated.getBalance() - withdrawAmount);
		currentMap.put(accKey, updated);

		AccountsUtil.writeToAccountList(currentMap);
	}

}
