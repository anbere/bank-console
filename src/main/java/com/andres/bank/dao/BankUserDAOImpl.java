package com.andres.bank.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.andres.bank.exceptions.UsernameTakenException;
import com.andres.bank.model.BankUser;
import com.andres.bank.util.BankUserUtil;


public class BankUserDAOImpl implements BankUserDAO{

	private final String filepath = "./files/bank_user.txt";
	
	@Override
	public boolean hasCorrectUsername(String username) {
		HashMap<String, BankUser> currentMap = BankUserUtil.currentBankUserMap();
		for(String key: currentMap.keySet())
		{
			if(key.equals(username))
			{
				return true;
			}

		}
		
		return false;
		
	}

	@Override
	public boolean hasCorrectPassword(String username, String password) {
		HashMap<String, BankUser> currentMap = BankUserUtil.currentBankUserMap();
		
		if(password.equals(currentMap.get(username).getPassword()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void createNewUser(String username, String password) throws UsernameTakenException{
		
		HashMap<String, BankUser> currentMap = new HashMap<>();
		BankUser user = new BankUser(username, password);
		
		//Reads in currentMap from file, if empty returns empty HashMap
		currentMap = BankUserUtil.currentBankUserMap();
		
		//Check if username is already in currentMap
		for(String key: currentMap.keySet())
		{
			if(key.equals(username))
			{
				throw new UsernameTakenException();
			}
		}
		
		//Adds new user to currentMap
		currentMap.put(user.getName(), user);
		
		//Writes new currentMap to file
		BankUserUtil.writeToBankUserList(currentMap);

	}

}










