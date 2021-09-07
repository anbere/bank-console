package com.andres.bank.ui;

import com.andres.bank.exceptions.InvalidPasswordException;
import com.andres.bank.exceptions.InvalidUsernameException;
import com.andres.bank.exceptions.UsernameTakenException;
import com.andres.bank.service.UserCreatorService;

public class RegistrationMenu implements Menu {

	@Override
	public void display() {
		String username;
		String password;
		boolean wasRegistered = false;
		
		UserCreatorService userAccess = new UserCreatorService();
		
		do {
			System.out.println("\n----- Registration Menu -----");
			System.out.println("\nType /'1/' to Cancel.");
			System.out.println("\nPlease enter you desired username: ");
			
			username = RegistrationMenu.scn.nextLine();
			
			if(username.equals("1"))
			{
				return;
			}
			
			System.out.println("\nType /'1/' to Cancel.");
			System.out.println("\nPlease enter you desired password: ");
			
			password = RegistrationMenu.scn.nextLine();
			
			if(password.equals("1"))
			{
				return;
			}
			
			try
			{
				userAccess.createUser(username, password);
				wasRegistered = true;
				System.out.println("\nUser created with the username: " + username);
			}catch(UsernameTakenException e)
			{
				System.out.println("\nUsername taken, try again with new username:");
				wasRegistered = false;
			}catch(InvalidUsernameException e)
			{
				System.out.println("\nInvalid username, no whitespace and empty username not allowed. Try again.");
				wasRegistered = false;
			}catch(InvalidPasswordException e)
			{
				System.out.println("\nInvalid password, no whitespace and empty username not allowed. Try again.");
				wasRegistered = false;
			}
			
			
			
		}while(!wasRegistered);
		
		
	}

}