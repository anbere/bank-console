package com.andres.bank.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.andres.bank.model.BankUser;

public class BankUserUtil {

	private static final String filepath = "./files/bank_user.txt";

	public static HashMap<String, BankUser> currentBankUserMap() {
		HashMap<String, BankUser> currentMap = new HashMap<>();

		try {
			FileInputStream fileIn = new FileInputStream(filepath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			currentMap = (HashMap<String, BankUser>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			// currentMap = null;
			return currentMap;
		} catch (ClassNotFoundException c) {
			System.out.println("ClassNotFoundException was reached");
			c.printStackTrace();
		}
		
		return currentMap;
	}
	
	public static void writeToBankUserList(HashMap<String, BankUser> currentMap)
	{
	
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream(filepath);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(currentMap);
	         out.close();
	         fileOut.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
}
