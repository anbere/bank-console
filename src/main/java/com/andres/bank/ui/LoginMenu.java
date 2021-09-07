package com.andres.bank.ui;

import com.andres.bank.exceptions.BlankEntryException;
import com.andres.bank.exceptions.IncorrectPasswordException;
import com.andres.bank.exceptions.UserNotFoundException;
import com.andres.bank.service.UserService;

public class LoginMenu implements Menu{

	public UserService userService = new UserService();
	
	@Override
	public void display() {
		String username;
		String password;
		boolean isCorrectUsername;
		boolean isCorrectPassword;
		boolean isLoggedIn = false;
		
		do
		{
			System.out.println("\n----- Login Menu -----");
			System.out.println("\nType \'1\' to exit.");
			System.out.println("\nPlease enter your username: ");
			
			username = LoginMenu.scn.nextLine();
			
			if(username.equals("1"))
			{
				break;
			}
			
			try
			{
				isCorrectUsername = userService.hasCorrectUsername(username);
				
				if(isCorrectUsername)
				{
					
					System.out.println("\nType \'1\' to exit.");
					System.out.println("\nPlease enter your password: ");
					
					password = LoginMenu.scn.nextLine();
					
					if(password.equals("1"))
					{
						break;
					}
					
					try
					{
						isCorrectPassword = userService.hasCorrectPassword(username, password);

						if(isCorrectPassword)
						{
							System.out.println("Login successful.");
							isLoggedIn = true;
						}
						
					}catch(IncorrectPasswordException e)
					{
						System.out.println(e.getMessage());
					}
					
				}
				
				
			}catch(BlankEntryException | UserNotFoundException e)
			{
				System.out.println(e.getMessage());
			}
			
			
			
		}while(!isLoggedIn);
		
		if(isLoggedIn)
		{
			Menu userMenu = new UserMenu(username);
			userMenu.display();
		}
		
	}

}
