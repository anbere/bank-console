package com.andres.bank.ui;

import java.sql.SQLException;

import com.andres.bank.exceptions.InvalidPasswordException;
import com.andres.bank.service.EmployeeService;
import com.andres.bank.util.GenerateUniqueIDUtil;

public class AddEmployeeMenu implements Menu {

	EmployeeService employeeService;
	
	public AddEmployeeMenu()
	{
		employeeService = new EmployeeService();
	}
	
	@Override
	public void display() {
		String employeeID;
		String employeePassword;
		boolean isAdmin = false;
		String choice = "0";
		
		
		
		do {
			System.out.println("Confirm you would like to create a new employee. Enter 'y' or 'n'.");
			choice = scan.nextLine();
			
			switch(choice.toLowerCase())
			{
			case "y":
				System.out.println("Enter a desired password for the new employee. 'e' to exit.");
				employeePassword = scan.nextLine();
				
				if(employeePassword.equalsIgnoreCase("e"))
				{
					break;
				}
				
				System.out.println("Will this employee be an admin? Enter 'y' or 'n'.");
				
				choice = scan.nextLine();
				switch(choice)
				{
				case "y":
					isAdmin = true;
					break;
				case "n":
					break;
				default:
					System.out.println("Invalid choice, try again.");
					choice = "0";
					break;
					
				}
				
				
				employeeID = (GenerateUniqueIDUtil.generateEmployeeID().toString());
				
				try {
					employeeService.createEmployee(Integer.parseInt(employeeID), employeePassword, isAdmin);
				} catch (NumberFormatException e) {

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (InvalidPasswordException e) {
					System.out.println(e.getMessage());
				}
				
				System.out.println("New employee ID is: " + employeeID);
				System.out.println("Do not lose this number.");
				break;
			case "n":
				break;
			default:
				System.out.println("Invalid menu choice, choose again.");
				choice = "0";
				break;
			}
		}while(choice.equals("0"));

	}

}
