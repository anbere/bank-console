package com.andres.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.andres.bank.exceptions.ProcessingApplicationException;
import com.andres.bank.model.Account;
import com.andres.bank.util.GenerateUniqueIDUtil;

public class AccountsDAOImpl implements AccountsDAO {

	@Override
	public boolean applyForNewAccount(String accountType, double initialBalance, String username, Connection con)
			throws ProcessingApplicationException, SQLException {

		String sql = "INSERT INTO bank_console.pending_accounts(account_owner,account_type,initial_balance,status) VALUES(?,?,?,'PENDING');";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, username);
		ps.setString(2, accountType);
		ps.setDouble(3, initialBalance);

		int count = ps.executeUpdate();

		if (count != 1) {
			return false;
		}

		return true;

	}

	@Override
	public ArrayList<Account> getActiveAccounts(String username, Connection con) throws SQLException {

		ArrayList<Account> accounts = new ArrayList<>();

		String sql = "SELECT * FROM bank_console.active_accounts WHERE account_owner = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int accountNumber = rs.getInt("account_number");
			String accountOwner = rs.getString("account_owner");
			String accountType = rs.getString("account_type");
			double balance = rs.getDouble("balance");
			String status = rs.getString("status");

			accounts.add(new Account(accountNumber, accountOwner, accountType, balance, status));
		}

		return accounts;
	}

	@Override
	public ArrayList<Account> getPendingAccounts(String username, Connection con) throws SQLException {

		ArrayList<Account> accounts = new ArrayList<>();

		String sql = "SELECT * FROM bank_console.pending_accounts WHERE account_owner = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			String accountOwner = rs.getString("account_owner");
			String accountType = rs.getString("account_type");
			double balance = rs.getDouble("initial_balance");
			String status = rs.getString("status");

			accounts.add(new Account(accountOwner, accountType, balance, status));
		}

		return accounts;
	}

	@Override
	public ArrayList<Account> getAccountsByUsername(String username, Connection con) throws SQLException {

		ArrayList<Account> accounts = new ArrayList<>();

		String sql = "SELECT * FROM bank_console.active_accounts WHERE account_owner = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);

		ResultSet rs = ps.executeQuery();

		while(rs.next()) {
			int accountNum = rs.getInt("account_number");
			String accountOwner = rs.getString("account_owner");
			String accountType = rs.getString("account_type");
			double balance = rs.getDouble("balance");
			String status = rs.getString("status");

			accounts.add(new Account(accountNum, accountOwner, accountType, balance, status));
		}
		
		sql = "SELECT * FROM bank_console.pending_accounts WHERE account_owner = ?;";
		ps = con.prepareStatement(sql);
		ps.setString(1, username);

		rs = ps.executeQuery();
		
		while(rs.next()) {
			int application_number = rs.getInt("application_number");
			String accountOwner = rs.getString("account_owner");
			String accountType = rs.getString("account_type");
			double balance = rs.getDouble("initial_balance");
			String status = rs.getString("status");

			accounts.add(new Account(accountOwner, application_number, accountType, balance, status));
		}

		return accounts;
	}

	@Override
	public Account getAccountByNumber(int accountNumber, Connection con) throws SQLException {

		Account account = null;

		String sql = "SELECT * FROM bank_console.active_accounts WHERE account_number = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, accountNumber);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			int accountNum = rs.getInt("account_number");
			String accountOwner = rs.getString("account_owner");
			String accountType = rs.getString("account_type");
			double balance = rs.getDouble("balance");
			String status = rs.getString("status");

			account = new Account(accountNum, accountOwner, accountType, balance, status);
		}

		return account;
	}

	@Override
	public void deposit(int accountNumber, double depositAmount, Connection con) throws SQLException {
		String sql = "SELECT balance FROM bank_console.active_accounts WHERE account_number = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, accountNumber);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			double currentBalance = rs.getDouble("balance");
			double updatedBalance = currentBalance + depositAmount;

			sql = "UPDATE bank_console.active_accounts SET balance = ? WHERE account_number = ?;";
			ps = con.prepareStatement(sql);

			ps.setDouble(1, updatedBalance);
			ps.setInt(2, accountNumber);

			int count = ps.executeUpdate();

			if (count != 1) {
				throw new SQLException("Deposit not updated.");
			}

		}

	}

	@Override
	public void withdraw(int accountNumber, double withdrawAmount, Connection con) throws SQLException {
		String sql = "SELECT balance FROM bank_console.active_accounts WHERE account_number = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, accountNumber);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			double currentBalance = rs.getDouble("balance");
			double updatedBalance = currentBalance - withdrawAmount;

			sql = "UPDATE bank_console.active_accounts SET balance = ? WHERE account_number = ?;";
			ps = con.prepareStatement(sql);

			ps.setDouble(1, updatedBalance);
			ps.setInt(2, accountNumber);

			int count = ps.executeUpdate();

			if (count != 1) {
				throw new SQLException("Deposit not updated.");
			}
		}

	}

	@Override
	public void updateAccountStatus(int applicationNumber, String decision, Connection con) throws SQLException {
		String sql = "UPDATE bank_console.pending_accounts SET status = ? WHERE application_number = ?;";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, decision);
		ps.setInt(2, applicationNumber);

		int count = ps.executeUpdate();

		if (count != 1) {
			throw new SQLException("Failed to update status of pending account.");
		}

		if (decision.equals("APPROVED")) {
			sql = "SELECT account_owner, account_type, initial_balance_cents FROM bank_app_data.pending_accounts WHERE application_number = ?;";

			ps = con.prepareStatement(sql);

			ps.setInt(1, applicationNumber);

			ResultSet rs = ps.executeQuery();

			int accountNumber = GenerateUniqueIDUtil.generateAccountNumber();

			if (rs.next()) {
				sql = "INSERT INTO bank_console.active_accounts VALUES(?,?,?,?,?);";

				ps = con.prepareStatement(sql);

				ps.setInt(1, accountNumber);
				ps.setString(2, rs.getString("account_owner"));
				ps.setString(3, rs.getString("account_type"));
				ps.setInt(4, rs.getInt("initial_balance"));
				ps.setString(5, "ACTIVE");

				count = ps.executeUpdate();
				if (count != 1) {
					throw new SQLException("Failed to create new active account.");
				}

			}
		} else {
			sql = "DELETE FROM bank_console.pending_accounts WHERE application_number = ?;";

			ps = con.prepareStatement(sql);
			ps.setInt(1, applicationNumber);

			int count2 = ps.executeUpdate();

			if (count2 != 1) {
				throw new SQLException("Failed to delete Denied application");
			}

		}

	}

}
