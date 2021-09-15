package com.andres.bank.ui;

import com.andres.bank.exceptions.IncorrectPasswordException;
import com.andres.bank.service.EmployeeService;

public class EmployeeAdminLoginMenu implements Menu {

	public EmployeeService employeeService = new EmployeeService();

	@Override
	public void display() {

		String employeeID;
		String password;
		boolean validEmployeeID;
		boolean validPassword;
		boolean isAdmin = false;
		boolean isLoggedIn = false;

		do {
			System.out.println("\n----- Employee Menu -----");
			System.out.println("\nType \'1\' to exit.");
			System.out.println("\nPlease enter your Employee ID: ");

			employeeID = EmployeeAdminLoginMenu.scan.nextLine();

			if (employeeID.equals("1")) {
				break;
			}

			try {
				validEmployeeID = employeeService.checkEmployeeID(Integer.parseInt(employeeID));
				if (validEmployeeID) {

					System.out.println("\nType \'1\' to exit.");
					System.out.println("\nPlease enter your password: ");

					password = LoginMenu.scan.nextLine();

					if (password.equals("1")) {
						break;
					}

					try {
						validPassword = employeeService.checkPassword(Integer.parseInt(employeeID), password);

						if (validPassword) {
							System.out.println("Login successful.");
							isAdmin = employeeService.checkAdmin(Integer.parseInt(employeeID));
							isLoggedIn = true;
						}

					} catch (IncorrectPasswordException e) {
						System.out.println(e.getMessage());
					}

				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} while (!isLoggedIn);

		if (isLoggedIn) {
			if (isAdmin) {
				Menu adminMainMenu = new AdminMainMenu();
				adminMainMenu.display();
			} else {
				Menu employeeMainMenu = new EmployeeMainMenu();
				employeeMainMenu.display();
			}

		}
	}

}
