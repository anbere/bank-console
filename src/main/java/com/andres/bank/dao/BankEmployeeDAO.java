package com.andres.bank.dao;

import com.andres.bank.model.BankEmployee;

public interface BankEmployeeDAO {
	public BankEmployee getEmployeeByEmployeeID(int employeeID);
}
