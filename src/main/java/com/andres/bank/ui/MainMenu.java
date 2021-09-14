package com.andres.bank.ui;

public class MainMenu implements Menu {

	@Override
	public void display(){
		
		System.out.println("----------Welcome to Bank ----------");
		String choice = "0";
		
		do 
		{
			System.out.println("\n----- MAIN MENU -----\n");
			System.out.println("1: Log In");
			System.out.println("2: New User");
			System.out.println("3: Employee Portal");
			System.out.println("4: Exit");

			choice = Menu.scan.nextLine().toLowerCase();
			
			switch(choice)
			{
			
			case "1":
				Menu loginMenu = new LoginMenu();
				loginMenu.display();
				choice = "0";
				break;
			case "2":
				Menu registrationMenu = new RegistrationMenu();
				registrationMenu.display();
				choice = "0";
				break;
			case "3":
				Menu employeeAdminMenu = new EmployeeAdminMenu();
				employeeAdminMenu.display();
				choice = "0";
				break;
			case "4":
				break;
			default:
				System.out.println("Not a valid option. Please try again.");
				choice = "0";
			
			}
			
			
		}while(choice.equals("0"));
		
		System.out.println("You have exited the banking application.");
	}

}







