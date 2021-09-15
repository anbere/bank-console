package com.andres.bank.ui;

import java.sql.SQLException;
import java.util.ArrayList;

import com.andres.bank.exceptions.NoAccountFoundException;
import com.andres.bank.model.Account;
import com.andres.bank.service.AccountActionsService;
import com.andres.bank.service.AccountInfoService;
import com.andres.bank.service.TransferService;

public class AccountMenu implements Menu {

	private String currentUser;
	boolean continueLoop = true;

	private AccountInfoService accountInfoService = new AccountInfoService();
	private AccountActionsService accountActionsService = new AccountActionsService();
	private TransferService transferService = new TransferService();

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

			System.out.println("\nCurrent Active Accounts:\n");

			for (Account account : userAccounts) {
				System.out.println(account);
			}

			System.out.println("\nPlease choose an account for more options, or 'e' to exit. ");
			choice = scan.nextLine();

			if(choice.equals("e"))
				break;
			
			try {
				Account chosenAccount = accountInfoService.getAccountByNumber(choice);

				if (chosenAccount != null) {
					do {
						choice = "4";
						System.out.println("\nFor account: " + chosenAccount + ", make a choice.");

						System.out.println("\n1: Deposit");
						System.out.println("2: Withdraw");
						System.out.println("3: Transfer");
						System.out.println("e: Exit");

						choice = Menu.scan.nextLine();

						switch (choice) {

						case "1":
							
							String depositAmount;
							System.out.println("Enter an amount to deposit, or '-1' to exit");
							depositAmount = scan.nextLine();

							if (depositAmount.equals("e")) {
								break;
							}

							try {
								accountActionsService.deposit(chosenAccount, depositAmount);
								System.out.println("Deposit of " + depositAmount + " successful.");
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}

							break;
							
						case "2":
							String withdrawAmount;
							System.out.println("Enter an amount to withdraw, or 'e' to exit");
							withdrawAmount = scan.nextLine();

							if (withdrawAmount.equals("e")) {
								break;
							}

							try {
								accountActionsService.withdraw(chosenAccount, withdrawAmount);
								System.out.println("Withdrawal of " + withdrawAmount + " successful.");
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
							
						case "3":
							String transferAccountNumber;
							System.out.println("Enter account number you wish to transfer to.");
							
							transferAccountNumber = scan.nextLine();

							try {
								Account receivingAccount = accountInfoService.getAccountByNumber(transferAccountNumber);
								System.out.println("Enter amount you wish to transfer.");
								String transferAmount = scan.nextLine();
								
								transferService.transfer(chosenAccount, receivingAccount, Double.parseDouble(transferAmount));
								System.out.println("Transfer of " + transferAmount + " successful.");
							}catch(Exception e)
							{
								System.out.println(e.getMessage());
							}
							
							break;
						case "e":
							break;
						default:
							System.out.println("Invalid choice, please choose again.");
							choice = "4";
						}

					} while (choice.equals("4"));
				}

			} catch (SQLException | NoAccountFoundException e) {
				System.out.println(e.getMessage());
			}

		} while (choice.equals("0"));

	}

}
