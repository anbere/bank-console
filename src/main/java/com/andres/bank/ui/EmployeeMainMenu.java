package com.andres.bank.ui;

import java.util.ArrayList;

import com.andres.bank.model.Account;
import com.andres.bank.service.AccountInfoService;
import com.andres.bank.service.UserService;

public class EmployeeMainMenu implements Menu {

	UserService userService;
	AccountInfoService accountInfoService;
	
	public EmployeeMainMenu() {
		userService = new UserService();
		accountInfoService = new AccountInfoService();
	}
	
	@Override
	public void display() {

		String choice = "0";
		
		System.out.println("----- Employee Main Menu -----");
		
		do {
			System.out.println("1: View customer accounts.");
			System.out.println("2: Approve or deny pending accounts.");
			System.out.println("e: Exit");
			
			choice = scan.nextLine();
			
			switch(choice)
			{
			case "1":
				boolean userFound = false;
				System.out.println("All registered usernames:");
				try {
					
					ArrayList<String> usernames = userService.getAllUsernames();
					for(String user: usernames)
					{
						System.out.println(user);
					}
					
					System.out.println("Choose a user to view their accounts. Or 'e' to exit");
					
					String username = scan.nextLine();
					
					userFound = userService.hasCorrectUsername(username);
					
					if(userFound)
					{
						try {
							for(Account account: accountInfoService.getAccountsByUsername(username))
							{
								System.out.println(account);
							}
							
							System.out.println("Enter a pending account to approve or deny, or 'e' to exit");
							
							choice = scan.nextLine(); 
						}catch(Exception e)
						{
							System.out.println(e.getMessage());
							e.printStackTrace();
						}
					}
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				break;
			case "2":
				break;
			case "e":
				break;
				default:
					System.out.println("Invalid menu choice.\n");
					choice = "0";
			}
			
			
		}while(choice.equals("0"));

	}

}
