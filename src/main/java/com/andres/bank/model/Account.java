package com.andres.bank.model;

import java.io.Serializable;

public class Account implements Serializable{

	private String accountOwner;
	private String accountType; //Checking or Savings
	private double balance;
	int status; // 0: pending, 1: active
	
	public Account(String type, double initialBalance, String username)
	{
		accountOwner = username;
		accountType = type;
		balance = initialBalance;
		status = 0;
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



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "\nAccount type: " + accountType + "\nCurrent Balance: " + balance;
	}


	
	
	
}
