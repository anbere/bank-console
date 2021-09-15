package com.andres.bank.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.andres.bank.exceptions.ProcessingApplicationException;
import com.andres.bank.model.Account;

public interface AccountsDAO {

	public boolean applyForNewAccount(String accountType, double initialBalance, String username, Connection con) throws ProcessingApplicationException, SQLException;

	public ArrayList<Account> getActiveAccounts(String username, Connection con) throws SQLException;
	
	public ArrayList<Account> getPendingAccounts(String username, Connection con) throws SQLException;
	
	public ArrayList<Account> getAccountsByUsername(String username, Connection con) throws SQLException;
	
	public Account getAccountByNumber(int accountNumber, Connection con) throws SQLException;

	public void deposit(int accountNumber, double depositAmount, Connection con) throws SQLException;
	
	public void withdraw(int accountnumber, double withdrawAmount, Connection con) throws SQLException;
	
	public void updateAccountStatus(int accountNumber, String decision, Connection con) throws SQLException;

	
}
