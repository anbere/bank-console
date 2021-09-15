package com.andres.bank.model;

public class BankEmployee {

	private int employeeID;
	private String password;
	private boolean isAdmin;
	
	public BankEmployee(int employeeID, String password) {
		super();
		this.employeeID = employeeID;
		this.password = password;
		this.isAdmin = false;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "BankEmployee [employeeID=" + employeeID + ", password=" + password + ", isAdmin=" + isAdmin + "]";
	}
	
	
	
	
}
