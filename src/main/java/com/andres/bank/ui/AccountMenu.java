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
		}

		if (!userAccounts.isEmpty()) {
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
					case "4":
						break;
					default:
						System.out.println("Invalid choice, choose again.");

					}
				} else {
					String accType = "0";
					System.out.println("CHECKING or SAVING?");
					do {
						System.out.println("'1' to Cancel");

						accType = AccountMenu.scn.nextLine();

						if (accType.equals("1")) {
							break;
						}

						if (accType.equals("CHECKING")) {
							System.out.println("In checking");
						} else if (accType.equals("SAVING")) {
							System.out.println("In savings");
							
						} else {
							System.out.println("Invalid choice, type CHECKING or SAVING");
							accType = "0";
						}
					} while(accType.equals("0"));

				}

			} while (choice.equals("0"));
		}

	}

}
