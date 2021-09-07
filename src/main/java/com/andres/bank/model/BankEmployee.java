package com.andres.bank.model;

import java.util.Objects;

public class BankEmployee {

	private int employeeID;
	private String firstName;
	private String lastName;
	
	public BankEmployee(int employeeID, String firstName, String lastName) {
		super();
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankEmployee other = (BankEmployee) obj;
		return employeeID == other.employeeID && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "BankEmployee [employeeID=" + employeeID + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
	
	
}
