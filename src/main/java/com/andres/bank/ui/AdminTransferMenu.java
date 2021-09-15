package com.andres.bank.ui;

import java.sql.SQLException;

import com.andres.bank.exceptions.InvalidInputException;
import com.andres.bank.exceptions.NoAccountFoundException;
import com.andres.bank.model.Account;
import com.andres.bank.service.AccountActionsService;
import com.andres.bank.service.AccountInfoService;
import com.andres.bank.service.TransferService;

public class AdminTransferMenu implements Menu {

	AccountInfoService accountInfoService;
	AccountActionsService accountActionsService;
	TransferService transferService;

	public AdminTransferMenu() {
		accountInfoService = new AccountInfoService();
		transferService = new TransferService();
		accountActionsService = new AccountActionsService();
	}

	@Override
	public void display() {
		String choice = "0";
		String sendingAccountNumber;
		String receivingAccountNumber;

		do {
			System.out.println("\nDo you want to:");
			System.out.println("1: Deposit to an account");
			System.out.println("2: Withdraw from an account");
			System.out.println("3: Transfer between accounts");
			System.out.println("e: Exit");

			choice = scan.nextLine();

			switch (choice) {
			case "1":
				String depositAmount;
				String chosenAccountNumber;

				System.out.println("Which account number would you like to deposit to? 'c' to cancel.");
				chosenAccountNumber = scan.nextLine();

				if (chosenAccountNumber.equalsIgnoreCase("c")) {
					choice = "0";
					break;
				}

				try {
					Account chosenAccount = accountInfoService.getAccountByNumber(chosenAccountNumber);

					System.out.println("Enter an amount to deposit, or 'c' to cancel");
					depositAmount = scan.nextLine();

					if (depositAmount.equals("c")) {
						choice = "0";
						break;
					}

					accountActionsService.deposit(chosenAccount, depositAmount);
					System.out.println("Deposit of " + depositAmount + " successful.");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (NumberFormatException nfe) {
					System.out.println("Not a valid account number, numeric values only.");
				} catch (InvalidInputException e) {
					System.out.println(e.getMessage());
				} catch (NoAccountFoundException e) {
					System.out.println(e.getMessage());
				}
				
				choice = "0";
				break;
			case "2":
				String withdrawAmount;
				String chosenAccountNumber1;

				System.out.println("Which account number would you like to withdraw from? 'c' to cancel.");
				chosenAccountNumber1 = scan.nextLine();

				if (chosenAccountNumber1.equalsIgnoreCase("c")) {
					choice = "0";
					break;
				}

				try {
					Account chosenAccount = accountInfoService.getAccountByNumber(chosenAccountNumber1);

					System.out.println("Enter an amount to withdraw, or 'c' to cancel");
					withdrawAmount = scan.nextLine();

					if (withdrawAmount.equals("c")) {
						choice = "0";
						break;
					}

					accountActionsService.withdraw(chosenAccount, withdrawAmount);
					System.out.println("Withdrawal of " + withdrawAmount + " successful.");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (NumberFormatException nfe) {
					System.out.println("Not a valid account number, numeric values only.");
				} catch (InvalidInputException e) {
					System.out.println(e.getMessage());
				} catch (NoAccountFoundException e) {
					System.out.println(e.getMessage());
				}
				
				choice ="0";
				break;
			case "3":
				System.out.println("Which account number would you like to transfer from? 'e' to exit.");

				sendingAccountNumber = scan.nextLine();

				if (sendingAccountNumber.equalsIgnoreCase("e")) {
					choice = "0";
					break;
				}

				System.out.println("Which account number would you like to transfer to? 'e' to exit");

				receivingAccountNumber = scan.nextLine();

				if (receivingAccountNumber.equalsIgnoreCase("e")) {
					choice = "0";
					break;
				}

				try {
					Account sendingAccount = accountInfoService.getAccountByNumber(sendingAccountNumber);
					Account receivingAccount = accountInfoService.getAccountByNumber(receivingAccountNumber);
					System.out.println("Enter amount you wish to transfer.");
					String transferAmount = scan.nextLine();

					transferService.transfer(sendingAccount, receivingAccount, Double.parseDouble(transferAmount));
					System.out.println("Transfer of " + transferAmount + " successful.");
					choice = "0";
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				choice = "0";
				break;
			case "e":
				break;
			default:
				System.out.println("Invalid menu choice, choose again.");
				choice = "0";
			}

		} while (choice.equals(0));

	}

}
