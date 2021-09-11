package com.andres.bank.ui;

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
			System.out.println("Current Active Accounts:\n");
			System.out.println(userAccounts);

		} catch (NoAccountFoundException e) {
			System.out.println(e.getMessage());
			return;
		}

		String choice = "0";
		do {
			if (userAccounts.size() == 1) {
				System.out.println("\nWhat would you like to do?");
				System.out.println("\n1: Deposit");
				System.out.println("2: Withdraw");
				System.out.println("3: Account Transfers");
				System.out.println("4: Exit");

				choice = AccountMenu.scn.nextLine();

				if (choice.equals("4")) {
					break;
				}

				switch (choice) {
				case "1":
					String depositAmount;
					System.out.println("Enter the amount you would like to deposit, or enter E to exit.");

					depositAmount = AccountMenu.scn.nextLine();
					double deposit = Double.parseDouble(depositAmount);

					if (depositAmount.equals("e") || depositAmount.equals("E")) {
						break;
					}

					try {
						accountActionsService.deposit(userAccounts.get(0), deposit);
						System.out.println("Deposit of " + depositAmount + "succesful.");
					} catch (BlankEntryException e) {
						System.out.println(e.getMessage());
					} catch (InvalidInputException e) {
						System.out.println(e.getMessage());
					}
					choice = "0";
					break;
				case "2":
					String withdrawAmount;
					System.out.println("Enter the amount you would like to withdraw, or enter E to exit.");

					withdrawAmount = AccountMenu.scn.nextLine();
					double withdraw = Double.parseDouble(withdrawAmount);

					if (withdrawAmount.equals("e") || withdrawAmount.equals("E")) {
						break;
					}
					
					try {
						accountActionsService.withdraw(userAccounts.get(0), withdraw);
						System.out.println("Your withdrawal of: " + withdrawAmount + " was successful.");
					} catch (BlankEntryException e) {
						System.out.println(e.getMessage());
					} catch (InvalidInputException e) {
						System.out.println(e.getMessage());
					}
					
					choice = "0";
					
					break;
				case "4":
					break;
				default:
					System.out.println("Invalid choice, choose again.");

				}
			} else {
				String accType = "0";
				System.out.println("\nWould you like to work with your CHECKING or SAVING account?");
				do {
					System.out.println("'1' to Cancel");

					accType = AccountMenu.scn.nextLine();

					if (accType.equals("1")) 
					{
						return;
					}
					
					System.out.println("\nWhat would you like to do?");
					System.out.println("\n1: Deposit");
					System.out.println("2: Withdraw");
					System.out.println("3: Account Transfers");
					System.out.println("4: Exit");

					choice = scn.nextLine();
					
					switch (choice) {
					case "1":
						String depositAmount;
						System.out.println("Enter the amount you would like to deposit, or enter E to exit.");

						depositAmount = AccountMenu.scn.nextLine();
						double deposit = Double.parseDouble(depositAmount);

						if (depositAmount.equals("e") || depositAmount.equals("E")) {
							break;
						}

						try {
							if(accType.equals("CHECKING")) {
								accountActionsService.deposit(userAccounts.get(0), deposit);
							} else if (accType.equals("SAVING"))
							{
								accountActionsService.deposit(userAccounts.get(1), deposit);
							}
							System.out.println("Deposit of " + depositAmount + " succesful.");
							break;
						} catch (BlankEntryException e) {
							System.out.println(e.getMessage());
						} catch (InvalidInputException e) {
							System.out.println(e.getMessage());
						}
						choice = "0";
						break;
					case "2":
						String withdrawAmount;
						System.out.println("Enter the amount you would like to withdraw, or enter E to exit.");

						withdrawAmount = AccountMenu.scn.nextLine();
						double withdraw = Double.parseDouble(withdrawAmount);

						if (withdrawAmount.equals("e") || withdrawAmount.equals("E")) {
							break;
						}
						
						try {
							if(accType.equals("CHECKING"))
							{
								accountActionsService.withdraw(userAccounts.get(0), withdraw);
							} else if (accType.equals("SAVING"))
							{
								accountActionsService.withdraw(userAccounts.get(1), withdraw);
							}
							System.out.println("Your withdrawal of: " + withdrawAmount + " was successful.");
							break;
						} catch (BlankEntryException e) {
							System.out.println(e.getMessage());
						} catch (InvalidInputException e) {
							System.out.println(e.getMessage());
						}
					case "4":
						break;
					default:
						System.out.println("Invalid choice, choose again.");

					}

				} while (accType.equals("0"));

			}

		} while (choice.equals("0"));

	}

}
