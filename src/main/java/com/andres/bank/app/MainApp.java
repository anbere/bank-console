package com.andres.bank.app;

import com.andres.bank.ui.MainMenu;
import com.andres.bank.ui.Menu;

public class MainApp {

	public static void main(String[] args) {
		
		Menu main = new MainMenu();

		try {
			main.display();
		} catch (Exception e) {
//			e.printStackTrace();
			System.err.println("oops");
		}
		
	}

}
