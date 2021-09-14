package com.andres.bank.model;

public class BankUser{
	private String username;
	private String password;
	//private ArrayList<String> accountIDs;
	
	public BankUser() {};
	
	public BankUser(String name, String password) {
		super();
		this.username = name;
		this.password = password;
	}

	public String getName() {
		return username;
	}

	public void setName(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "BankUser [name=" + username + ", password=" + password + "]";
	}
	
}
