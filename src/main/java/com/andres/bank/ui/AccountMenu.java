package com.andres.bank.ui;

import java.sql.SQLException;
import java.util.ArrayList;

import com.andres.bank.exceptions.BlankEntryException;
import com.andres.bank.exceptions.InvalidInputException;
import com.andres.bank.exceptions.NoAccountFoundException;
import com.andres.bank.model.Account;
import com.andres.bank.service.AccountActionsService;
import com.andres.bank.service.AccountInfoService;

public class AccountMenu implements Menu {

	private String currentUser;
	boolean continueLoop = true;

	private AccountInfoService accountInfoService = new AccountInfoService();
	private AccountActionsService accountActionsService = new AccountActionsService();

	public AccountMenu(String username) {
		currentUser = username;
	}

	@Override
	public void display() {

		ArrayList<Account> userAccounts = new ArrayList<>();

		System.out.println("\n----- Account Menu -----");

		try {
			userAccounts = accountInfoService.getActiveAccounts(currentUser);
		} catch (NoAccountFoundException e) {
			System.out.println(e.getMessage());
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String choice = "0";
		do {
			
			System.out.println("Current Active Accounts:\n");
		
			for(Account account: userAccounts)
			{
				System.out.println(account);
			}
			
			System.out.println("Please choose an account for more options: ");
			choice = scan.nextLine();
			
			try 
			{
				Account chosenAccount = accountInfoService.getAccountByNumber(choice, currentUser);
				
				if(chosenAccount != null)
				{
					do
					{
						choice = "4";
						System.out.println("\nFor account: " + chosenAccount + ", make a choice.");
						
						System.out.println("\n1: Deposit");
						System.out.println("2: Withdraw");
						System.out.println("3: Transfer");
						System.out.println("e: Exit");

						choice = Menu.scan.nextLine();
						
						switch(choice)
						{
						
						case "1":
							Double depositAmount;
							System.out.println("Enter an amount to deposit, or 'e' to exit");
							depositAmount = Double.parseDouble(scan.nextLine());
							
							if(depositAmount.equals("e"))
							{
								break;
							}
							
							try
							{
								accountActionsService.deposit(chosenAccount, depositAmount);
								System.out.println("Deposit successful.");
							}catch(Exception e)
							{
								System.out.println(e.getMessage());
							}
							
							break;
						case "2":
							break;
						case "3":
							break;
						case "e":
							break;
						default:
							System.out.println("Invalid choice, please choose again.");
							choice = "4";
						}
						
					}while(choice.equals("4"));
				}
				
			} catch (SQLException | NoAccountFoundException e) 
			{
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			


		} while (choice.equals("0"));

	}

}
