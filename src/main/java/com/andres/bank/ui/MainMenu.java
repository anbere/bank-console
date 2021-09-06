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

			choice = Menu.scn.nextLine().toLowerCase();
			
			switch(choice)
			{
			
			case "1":
//				Menu loginMenu = new LoginMenu();
//				loginMenu.display();
				System.out.println("Login Menu");
				choice = "0";
				break;
			case "2":
//				Menu registrationMenu = new RegistrationMenu();
//				registrationMenu.display();
				System.out.println("Registration Menu");
				choice = "0";
				break;
			case "3":
//				Menu employeeLoginMenu = new EmployeeLoginMenu();
//				employeeLoginMenu.display();
				System.out.println("Employee Menu");
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







