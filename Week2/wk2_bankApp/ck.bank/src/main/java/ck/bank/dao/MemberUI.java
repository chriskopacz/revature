package ck.bank.dao;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import ck.bank.driver.Main;
import ck.bank.pojos.Account;
import ck.bank.pojos.User;



public class MemberUI {
	User loggedIn;

	public MemberUI(User u)
	{
		super();
		this.loggedIn = u;
	}
	
	public void topUI()
	{
		int userInput = -1;
		boolean valid = false;

		while(!valid)
		{
			userInput = -1;
			System.out.println("\nMEMBER INTERFACE");
			System.out.println("Make your selection:");
			System.out.println("1 - Display Member Information");
			System.out.println("2 - Change Username");
			System.out.println("3 - Change First Name");
			System.out.println("4 - Change Last Name");
			System.out.println("5 - Check Balance");
			System.out.println("6 - Access Accounts");
			System.out.println("0 - Exit");
			try
			{
				userInput = Main.sc.nextInt();
				if(userInput >= 0 && userInput <= 6)
				{
					System.out.println("\nGood selection: "+userInput + "\n");
					valid = true;
				}else
				{
					System.out.println("\nBad, need 0-6. Try again.\n");
					Main.sc.nextLine();
				}//if-else
			}catch(InputMismatchException ime)
			{
				System.out.println("\nNon-int input. Try again.\n");
				Main.sc.nextLine();
			}//try-catch



			//switch statement
			switch(userInput)
			{
			case 1:
				//display account information
				displayAccountInformation();
				valid = false;
				break;
			case 2:
				//change username
				changeUsername();
				valid = false;
				break;
			case 3:
				//change first name
				changeFirstName();
				valid = false;
				break;
			case 4:
				//change last name
				changeLastName();
				valid = false;
				break;
			case 5:
				//check balance
				checkBalance();
				valid = false;
				break;
			case 6:
				//access accounts
				AccountUI aui = new AccountUI(this.loggedIn);
				aui.topUI();
				valid = false;
			case 0:
				//exit MemberUI
				System.out.println("Exit MemberUI");
				break;
			default:
				System.out.println("MemberUI - default");
			}//switch


		}//while

		//return userInput;
	}//topUI

	//==========================================================

	public void displayAccountInformation()
	{
		System.out.println("Your account information:");
		System.out.println("  Username = " + this.loggedIn.getUsername());
		System.out.println("First Name = " + this.loggedIn.getFirstName());
		System.out.println(" Last Name = " + this.loggedIn.getLastName());
		
	}//display account information

	//==========================================================
	
	public void checkBalance()
	{
		List<Account> accounts = Main.aDao.getAllAccounts(this.loggedIn.getUserId());
		double accountsTotal = 0;
		
		for(Account a : accounts)
		{
			accountsTotal += a.getBalance();
		}
		
		System.out.println("Total funds available across all accounts:\n" + accountsTotal);
	}//check balance

	//=====================================================================

	public void changeFirstName()
	{	
		ClientUI cui = new ClientUI();
		String newF = "";
		String uname = this.loggedIn.getUsername();
		
		newF = cui.validateFirstName();
		this.loggedIn.setFirstName(newF);
		Main.uDao.updateFirstName(newF,uname);
		System.out.println("first name changed");
	}//changeFirstName

	//==================================================================

	public void changeLastName()
	{
		ClientUI cui = new ClientUI();
		String newL = "";
		String uname = this.loggedIn.getUsername();
		
		newL = cui.validateLastName();
		this.loggedIn.setLastName(newL);
		Main.uDao.updateLastName(newL,uname);
		System.out.println("last name changed");
	}//changeLastName
	
	//==================================================================
	
	public void changeUsername()
	{
		ClientUI cui = new ClientUI();
		String newU = "";
		String old = this.loggedIn.getUsername();
		
		
		
		newU = cui.validateUsername();
		this.loggedIn.setUsername(newU);
		Main.uDao.updateUsername(newU,old);
		System.out.println("username changed");
		
		//use prepared statement to update db with new username
		
	}//changeUsername
	
}//MemberUI
