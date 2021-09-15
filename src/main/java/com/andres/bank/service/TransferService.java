package com.andres.bank.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.andres.bank.dao.AccountsDAO;
import com.andres.bank.dao.AccountsDAOImpl;
import com.andres.bank.exceptions.InvalidInputException;
import com.andres.bank.exceptions.NoAccountFoundException;
import com.andres.bank.model.Account;
import com.andres.bank.util.ConnectionUtil;

public class TransferService {

	AccountInfoService info = new AccountInfoService();
	AccountActionsService action = new AccountActionsService();
	AccountsDAO accountsDAO = new AccountsDAOImpl();
	
	public void transfer(Account sendingAccount, Account receivingAccount, double transferAmount) throws SQLException, NoAccountFoundException, InvalidInputException
	{
		try(Connection con = ConnectionUtil.getConnection())
		{
			double sendingBalance = sendingAccount.getBalance();
						
			if(sendingBalance < transferAmount)
			{
				throw new InvalidInputException("Transfer amount exceeds your balance.");
			}
			
			action.withdraw(sendingAccount, String.valueOf(transferAmount));
			action.deposit(receivingAccount, String.valueOf(transferAmount));
		}
	}
}
