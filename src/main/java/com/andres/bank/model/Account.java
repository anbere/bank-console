package com.andres.bank.model;

public class Account{

	private int    applicationNumber; //Pending Account
	private int    accountNumber;     //Active Account
	private String accountOwner;
	private String accountType;       //Checking or Savings
	private double balance;
	private String accountStatus;     //ACTIVE, PENDING, CLOSED
	
	public Account(int accNumber, String username, String type, double balance, String status) // Active Account Constructor
	{
		accountNumber = accNumber;
		accountOwner = username;
		accountType = type;
		this.balance = balance;
		accountStatus = status;
	}
	
	public Account(String username, int appNumber, String type, double initialBalance, String status) // Pending Account Constructor
	{
		applicationNumber = appNumber;
		accountOwner = username;
		accountType = type;
		balance = initialBalance;
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

	@Override
	public String toString() {

			if (this.accountStatus.equals("PENDING")) { // PENDING ACCOUNT
				return "[Application #:" + applicationNumber + " | " + accountType + " | Pending Initial Balance: " + balance + " | Application Status:"
						+ accountStatus + "]";
			} else if (this.accountStatus.equals("ACTIVE")) { // ACTIVE ACCOUNT
				return "[Account #: " + accountNumber + " | " + accountType + " | Balance: " + balance + " | Account Status: " + accountStatus + "]";
			} else { // CLOSED ACCOUNT
				return "[Account #: " + accountNumber + " | " + accountType + " | Account Status: " + accountStatus + "]";
			}
		
		
	}

}
