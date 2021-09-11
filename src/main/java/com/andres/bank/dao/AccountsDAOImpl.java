package com.andres.bank.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.andres.bank.exceptions.ProcessingApplicationException;
import com.andres.bank.model.Account;
import com.andres.bank.util.AccountsUtil;

public class AccountsDAOImpl implements AccountsDAO {

	@Override
	public boolean applyForNewAccount(String accountType, double initialBalance, String username) throws ProcessingApplicationException 
	{
		HashMap<String, Account> currentMap = new HashMap<>();
		Account account = new Account(accountType, initialBalance, username);		
		String uniqID = username + accountType;
		
		//Reads in currentMap from accounts
		currentMap = AccountsUtil.currentAccountsMap();
		
		for(String key: currentMap.keySet())
		{
			if(key.equals(uniqID))
			{
				throw new ProcessingApplicationException(accountType + " account already applied for. Please wait for processing.");
			}
		}
		
		//Adds new pending account

		currentMap.put(uniqID, account);
		
		//Writes to account file new currentMap
		AccountsUtil.writeToAccountList(currentMap);
		
		return true;
	}

	@Override
	public ArrayList<Account> getActiveAccounts(String username) {
		HashMap<String, Account> currentMap = AccountsUtil.currentAccountsMap();
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		String key1 = username + "CHECKING";
		String key2 = username + "SAVING";
		
		for(String key: currentMap.keySet())
		{
			if(key.equals(key1))
			{
				accounts.add(currentMap.get(key1));
			}else if(key.equals(key2))
			{
				accounts.add(currentMap.get(key2));
			}
			
		}
		
		if(accounts.size() == 2)
		{
			if(accounts.get(0).getAccountType().equals("SAVING"))
			{
				Collections.swap(accounts, 0, 1);
			}
		}
		
		return accounts;
	}

	@Override
	public void deposit(double depositAmount, String accKey) {
		HashMap<String, Account> currentMap = AccountsUtil.currentAccountsMap();
		Account updated = currentMap.get(accKey);
		
		updated.setBalance(updated.getBalance() + depositAmount);
//		currentMap.remove(accKey);
		currentMap.put(accKey, updated);
		
		AccountsUtil.writeToAccountList(currentMap);
	}
	
	public void withdraw(double withdrawAmount, String accKey)
	{
		HashMap<String, Account> currentMap = AccountsUtil.currentAccountsMap();
		Account updated = currentMap.get(accKey);
		
		updated.setBalance(updated.getBalance() - withdrawAmount);
		currentMap.put(accKey, updated);
		
		AccountsUtil.writeToAccountList(currentMap);
	}


}
