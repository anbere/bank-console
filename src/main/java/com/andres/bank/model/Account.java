package com.andres.bank.model;

public class Account{

	private int    applicationNumber;
	private int    applicationStatus;
	private int    accountNumber;
	private String accountOwner;
	private String accountType; //Checking or Savings
	private double balance;
	private String accountStatus; // 0: pending, 1: active, 3: closed
	
	public Account(String status, int accNumber, String type, double initialBalance, String username)
	{
		accountNumber = accNumber;
		accountOwner = username;
		accountType = type;
		balance = initialBalance;
		accountStatus = status;
	}
	
	public Account(String status, String type, double initialBalance, String username)
	{
		accountStatus = status;
		accountOwner = username;
		accountType = type;
		balance = initialBalance;
	}
	
	public String getAccountStatus() {
		return accountStatus;
	}



	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
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
