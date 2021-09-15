package com.andres.bank.ui;

import java.sql.SQLException;
import java.util.ArrayList;

import com.andres.bank.exceptions.NoAccountFoundException;
import com.andres.bank.model.Account;
import com.andres.bank.service.AccountActionsService;
import com.andres.bank.service.AccountInfoService;
import com.andres.bank.service.UserService;

public class AdminMainMenu implements Menu {

	UserService userService;
	AccountInfoService accountInfoService;
	AccountActionsService accountActionsService;

	public AdminMainMenu() {
		userService = new UserService();
		accountInfoService = new AccountInfoService();
		accountActionsService = new AccountActionsService();
	}

	@Override
	public void display() {

		String choice = "0";
		String decision = "0";
		System.out.println("\n----- Employee Main Menu -----");

		do {
			System.out.println("\n1: View customer accounts.");
			System.out.println("2: Approve or deny pending accounts.");
			System.out.println("e: Exit");

			choice = scan.nextLine();

			switch (choice) {
			case "1":
				System.out.println("\nAll registered usernames:");
				try {

					ArrayList<String> usernames = userService.getAllUsernames();
					for (String user : usernames) {
						System.out.println(user);
					}

					System.out.println("\nChoose a user to view their accounts. Or 'e' to exit");

					String username = scan.nextLine();

					if (username.equalsIgnoreCase("e")) {
						choice = "0";
						break;
					}

					userService.hasCorrectUsername(username); // throws exception if fails

					for (Account account : accountInfoService.getAccountsByUsername(username)) {
						System.out.println(account);
					}

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				choice = "0";
				break;
			case "2":
				do {
					try {

						System.out.println("\nEnter an application number to approve or deny, or 'c' to cancel");

						choice = scan.nextLine();

						if (choice.equalsIgnoreCase("c")) {
							choice = "0";
							break;
						}

						Account account = accountInfoService.getApplicationByNumber(choice);

						System.out.println("\nWould you like to approve or deny the account?");
						System.out.println("1: APPROVE");
						System.out.println("2: DENY");
						System.out.println("e: Exit");

						decision = scan.nextLine();

						switch (decision) {
						case "1":
							accountActionsService.updateAccountStatus(account.getApplicationNumber(), "ACTIVE");
							System.out.println("\nAccount has been succesfully approved.");
							choice = "0";
							break;
						case "2":
							accountActionsService.updateAccountStatus(account.getApplicationNumber(), "DENIED");
							System.out.println("\nAccount has been succesfully denied.");
							choice = "0";
							break;
						default:
							System.out.println("\nInvalid menu choice.");
							decision = "0";
							break;

						}

					} catch (SQLException e) {
						System.out.println(e.getMessage());
					} catch (NoAccountFoundException e) {
						System.out.println(e.getMessage());
					} catch (NumberFormatException nfe) {
						System.out.println("Not a valid account number, numeric values only.");
					}
				} while (decision.equals("0"));

				break;
			case "e":
				break;
			default:
				System.out.println("Invalid menu choice.\n");
				choice = "0";
			}

		} while (choice.equals("0"));

	}

}
