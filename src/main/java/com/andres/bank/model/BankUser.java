package com.andres.bank.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class BankUser implements Serializable{
	private String username;
	private String password;
	private ArrayList<String> accountIDs;
	
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankUser other = (BankUser) obj;
		return Objects.equals(username, other.username) && Objects.equals(password, other.password);
	}
	
	
}
