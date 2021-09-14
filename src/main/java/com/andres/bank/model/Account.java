package com.andres.bank.model;

public class Account{

	private int    applicationNumber;
	private int    accountNumber;
	private String accountOwner;
	private String accountType; //Checking or Savings
	private double balance;
	private String accountStatus; // 0: pending, 1: active, 3: closed
	private String applicationStatus;
	
	public Account(int accNumber, String username, String type, double balance, String status) // Active Account Constructor
	{
		accountNumber = accNumber;
		accountOwner = username;
		accountType = type;
		this.balance = balance;
		accountStatus = status;
	}
	
	public Account(String username, String type, double initialBalance, String status) // Pending Account Constructor
	{
		accountOwner = username;
		accountType = type;
		balance = initialBalance;
		accountStatus = status;
	}
	
	
	
	public int getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(int applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	@Override
	public String toString() {

			if (this.accountStatus.equals("PENDING")) { // PENDING ACCOUNT
				return "[#" + applicationNumber + " | " + accountType + " | Pending Initial Balance: " + balance + " | Application Status:"
						+ applicationStatus + "]";
			} else if (this.accountStatus.equals("ACTIVE")) { // ACTIVE ACCOUNT
				return "[Account: " + accountNumber + " | " + accountType + " | Balance: " + balance + "]";
			} else { // CLOSED ACCOUNT
				return "[Account: " + accountNumber + " | " + accountType + "]";
			}
		
		
	}

}
