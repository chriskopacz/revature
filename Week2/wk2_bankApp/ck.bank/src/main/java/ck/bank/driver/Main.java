package ck.bank.driver;

import java.util.List;
import java.util.Scanner;

import ck.bank.dao.AccountDao;
import ck.bank.dao.AccountDaoImpl;
import ck.bank.dao.ClientUI;
import ck.bank.dao.MemberUI;
import ck.bank.dao.UserDao;
import ck.bank.dao.UserDaoImpl;
import ck.bank.pojos.User;


public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static UserDao uDao = new UserDaoImpl();
	public static AccountDao aDao = new AccountDaoImpl();
	
	public static void main(String[] args)
	{
		List<User> users;
		
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
			cui.createNewUser();
			System.out.println("User created successfully");
			//start member ui
			break;
		case 2:
			//login for existing user

			loggedIn = cui.userLogin();
			if(loggedIn != null)
			{
				System.out.println("\n\nLogin successful.\nHello, " + loggedIn.getUsername());
				MemberUI mui = new MemberUI(loggedIn);
				mui.topUI();
				//call memberUI for logged in user
			}else
			{
				System.out.println("User not found - exiting");
			}
			//break;
		case 3:
			//exit - print message/do nothing
			System.out.println("\n\nExiting program. Thank you.\n\n");
			break;
		default:
			System.out.println("Something went wrong");
		}//switch

		
		
		
		
		

		
//		//print user information -- for testing only
//		users = uDao.getAllUsers();
//		
//		for(User u : users)
//		{
//			System.out.println(u.toString());
//		}
		
		
		sc.close();
	}//main method
}