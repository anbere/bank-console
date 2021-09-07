package com.andres.bank.ui;

import com.andres.bank.exceptions.ProcessingApplicationException;
import com.andres.bank.service.AccountApplicationService;

public class AccountApplicationMenu implements Menu {

	String currentUser;
	
	AccountApplicationService aps = new AccountApplicationService();

	public AccountApplicationMenu(String username) {
		currentUser = username;
	}

	@Override
	public void display() {

		String choice = "0";
		String accountType = null;
		String initialBalance = null;
		boolean successfullyApplied = false;

		do
		{
			System.out.println("What type of account would you like to apply for?");
			System.out.println("1: Checking Account");
			System.out.println("2: Savings Account");
			System.out.println("3: Exit");
			
			choice = AccountApplicationMenu.scn.nextLine();
			
			switch (choice) {
			case "1":
				accountType = "CHECKING";

				do {
					System.out.println("1: Cancel");
					System.out.println("Enter initial deposit amount: ");
					initialBalance = AccountApplicationMenu.scn.nextLine();

					if (initialBalance.equals("1"))
					{
						break;
					}
					
					try
					{
						successfullyApplied = aps.applyForNewAccount(accountType, Double.parseDouble(initialBalance), currentUser);
					}catch(ProcessingApplicationException e)
					{
						System.out.println(e.getMessage());
						//successfullyApplied = true;
						break;
					}
				
				} while (!successfullyApplied);

				if (successfullyApplied) {
					
					System.out.println(
							"Checking account application succesfully received.");
				}
				break;
			case "2":
				accountType = "SAVING";

				do {
					System.out.println("1: Cancel");
					System.out.println("Enter initial deposit ammount: ");
					initialBalance = AccountApplicationMenu.scn.nextLine();

					if (initialBalance.equals("1"))
					{
						break;
					}

					try
					{
						successfullyApplied = aps.applyForNewAccount(accountType, Double.parseDouble(initialBalance), currentUser);
					}catch(ProcessingApplicationException e)
					{
						System.out.println(e.getMessage());
						//successfullyApplied = true;
						break;
					}

				} while (!successfullyApplied);
				if (successfullyApplied) {

					System.out.println(
							"Savings account application successfully recieved.");
				}
				break;
			case "3":
				break;
			default:
				System.out.println("Not a valid option. Please try again.");
				choice = "0";
			}
			
			
		}while(choice.equals("0"));
		
	}

}
