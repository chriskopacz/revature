package ck.bankApp;

import java.util.List;
import java.util.Scanner;

public class Main {
	public static UserIO io = new UserIO();
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		//UserIO io = new UserIO();
		
		List<User> users = io.readAllUsers();// keep this

		/*
		for(User u : users)
		{
			u.displayAccountInformation();
			u.checkBalance();
		}	
		
		System.out.println("====================\n====================");
		User bb = new User("j","K","L",4.0);
		users.add(bb);
		
		for(User u: users)
		{
			u.displayAccountInformation();
			u.checkBalance();
		}
		
		io.writeAllUsers(users);
		System.out.println("\n\nwritten to file");
		*/
		
		//initialize top level client UI - make a selection to create new account, login with an existing account, or exit
		ClientUI cui = new ClientUI();
		int topSelection;
		User loggedIn;
		boolean userExit = false; //maybe put the topUI in a loop until the user manually exits?
		
		topSelection = cui.topUI();

		switch(topSelection)
		{
		case 1:
			//create new user
			users = cui.createNewUser(users);
			System.out.println("User created successfully");
			//start member ui
			break;
		case 2:
			//login for existing user
			//need to find user via username
			loggedIn = cui.userLogin(users);
			if(loggedIn != null)
			{
				System.out.println("\n\nLogin successful.\nHello, " + loggedIn.getUsername());
				MemberUI mui = new MemberUI(loggedIn);
				mui.topUI();
				//call memberUI for logged in user
			}
			//set as currentUser
			//start memberUI
			break;
		case 3:
			//exit - print message/do nothing
			System.out.println("\n\nExiting program. Thank you.\n\n");
			break;
		default:
			System.out.println("Something went wrong");
		}//switch

		System.out.println("Writing accounts.txt");
		io.writeAllUsers(users);
		sc.close();
	}//main method
}
