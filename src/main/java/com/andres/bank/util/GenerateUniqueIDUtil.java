package com.andres.bank.util;

import java.util.UUID;

public class GenerateUniqueIDUtil {

	
	public static Long generateEmployeeID()
	{
		Long id = 0L;
		UUID temp = UUID.randomUUID();

		id = Math.abs(temp.getMostSignificantBits());
	

		id = Long.parseLong("9" + id.toString().substring(0, 5));

		return id;	
	}
	
	public static int generateAccountNumber()
	{
		Long id = 0L;
		UUID temp = UUID.randomUUID();

		id = Math.abs(temp.getMostSignificantBits());
	

		int accountNumber = Integer.parseInt("1" + id.toString().substring(0, 5));

		return accountNumber;
	}

}
