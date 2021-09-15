package com.andres.bank.ui;

import java.sql.SQLException;
import java.util.ArrayList;

import com.andres.bank.exceptions.NoAccountFoundException;
import com.andres.bank.model.Account;
import com.andres.bank.service.AccountInfoService;

public class UserMenu implements Menu {

	private String username;
	AccountInfoService accountInfoService;
	
	public UserMenu(String username) {
		this.username = username;
		accountInfoService = new AccountInfoService();
	}

	@Override
	public void display() {
		
		String choice = "0";
		
		System.out.println("Welcome " + username + "!\nPlease choose an option.");
		
		do 
		{
			
			System.out.println("\n----- User Main Menu -----");
			System.out.println("1: Apply for a new account");
			System.out.println("2: View my active account(s)");
			System.out.println("3: View pending account application(s)");
			System.out.println("4: Exit");
			
			choice = UserMenu.scan.nextLine();
			
			switch (choice) {
			case "1":
				Menu accountApplicationMenu = new AccountApplicationMenu(username);
				accountApplicationMenu.display();
				choice = "0";
				break;
			case "2":
				Menu accountMenu = new AccountMenu(username);
				accountMenu.display();
				choice = "0";
				break;
			case "3": 
				try {
					for (Account account : accountInfoService.getPendingAccounts(username)) {
						System.out.println(account);
					}
				}catch (NoAccountFoundException e)
				{
					System.out.println(e.getMessage());
				}catch(SQLException e2)
				{
					System.out.println(e2.getMessage());
				}
				choice = "0";
				break;
			case "4":
				break;
			default:
				System.out.println("Not a valid option. Please try again");
				choice = "0";
			}
			
			
			
		}while(choice.equals("0"));
	}

}
