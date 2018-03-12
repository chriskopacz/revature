package ck.bankApp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MemberUI {
	User loggedIn;
	
	public MemberUI(User u)
	{
		this.loggedIn = u;
	}
	public void topUI()
	{
		int userInput = -1;
		boolean valid = false;
		
		while(!valid)
		{
			userInput = -1;
			System.out.println("\n\nMake your selection:");
			System.out.println("1 - Display Account Information");
			System.out.println("2 - Check Balance");
			System.out.println("3 - Change First Name");
			System.out.println("4 - Change Last Name");
			System.out.println("5 - Withdraw Funds");
			System.out.println("6 - Deposit Funds");
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
				//check balance
				checkBalance();
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
				//withdraw funds
				withdrawFunds();
				valid = false;
				break;
			case 6:
				//deposit funds
				depositFunds();
				valid = false;
				break;
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
		System.out.println("Available balance: $" + this.loggedIn.getBalance());
	}//check balance
	
	//==========================================================
	
	public void withdrawFunds()
	{
		double amountToWithdraw = 0;
		boolean valid = false;
		System.out.println("Available balance: $" + this.loggedIn.getBalance());
		System.out.println("Your account cannot overdraw more than $100.00");
		System.out.println("Enter amount to withdraw:");
		
		while(!valid)
		{
			try
			{
				amountToWithdraw = Main.sc.nextDouble();
				if((this.loggedIn.getBalance()-amountToWithdraw) < 100)
				{
					System.out.println("\n\nYou cannot withdraw that much");
					System.out.println("Your account cannot overdraw more than $100.00");
					System.out.println("Available Balance: $" + this.loggedIn.getBalance());
					System.out.println("Enter amount to withdraw:");
					Main.sc.nextLine();
				}else if(amountToWithdraw < 0)
				{
					System.out.println("\n\nNegative input");
					System.out.println("You cannot withdraw negative amounts!");
					System.out.println("Available Balance: $" + this.loggedIn.getBalance());
					System.out.println("Enter amount to withdraw:");
					Main.sc.nextLine();
				}else
				{
					this.loggedIn.setBalance(this.loggedIn.getBalance()-amountToWithdraw);
					System.out.println("\n\nWithdrawal completed successfully.");
					System.out.println("Your new available balance is: $" + this.loggedIn.getBalance());
					valid = true;
				}//if-else
			}catch(InputMismatchException ime)
			{
				System.out.println("\n\nNon-number input");
				System.out.println("Your account cannot overdraw more than $100.00");
				System.out.println("Available balance: $" + this.loggedIn.getBalance());
				System.out.println("Enter the amount to withdraw:");
				Main.sc.nextLine();
			}//try-catch
		}//while
		//sc.close();
	}//withdraw funds
	
	//=================================================================
	
	public void depositFunds()
	{
		double amountToDeposit;
		boolean valid = false;
		System.out.println("Current balance: $" + this.loggedIn.getBalance());
		System.out.println("Enter amount to deposit:");
		
		while(!valid)
		{
			try
			{
				amountToDeposit = Main.sc.nextDouble();
				if(amountToDeposit < 0)
				{
					System.out.println("\n\nNegative input");
					//System.out.println("You cannot deposit negative amounts!");
					System.out.println("Current Balance: $" + this.loggedIn.getBalance());
					System.out.println("Enter amount to deposit:");
				}else
				{
					this.loggedIn.setBalance(this.loggedIn.getBalance() + amountToDeposit);
					System.out.println("\n\nDeposit completed successfully");
					System.out.println("Your new balance is: $" + this.loggedIn.getBalance());
					valid = true;
				}//if-else
				
			}catch(InputMismatchException ime)
			{
				//System.out.println("--INVALID ENTRY--INVALID ENTRY--");
				//System.out.println("================================");
				System.out.println("\n\nNon-number input");
				System.out.println("Current balance is: $" + this.loggedIn.getBalance());
				System.out.println("Enter amount to deposit:");
				Main.sc.nextLine();
			}//try-catch
		}//while
		//sc.close();
	}//deposit funds
	
	//=====================================================================
	
	public void changeFirstName()
	{
		String newF;
		boolean valid = false;
		
		while(!valid)
		{
			System.out.println("\n\nEnter first name: (1-12 characters, letters only)");
			newF = Main.sc.nextLine();
			if(newF.length() >= 1 && newF.length() <= 10)
			{
				if(newF.matches("[A-Za-z]*"))
				{
					System.out.println("Good first name: " + newF +"\n");
					this.loggedIn.setFirstName(newF);
					valid = true;
				}else
				{
					System.out.println("\nIllegal characters\n");
				}//if-else
			}else
			{
				System.out.println("\nBad length\n");
			}//if-else
		}//while
	}//changeFIrstName
	
	//==================================================================
	
	public void changeLastName()
	{
		String newL;
		boolean valid = false;
		
		while(!valid)
		{
			System.out.println("\n\nEnter last name: (1-12 characters, letters only)");
			newL = Main.sc.nextLine();
			if(newL.length() >= 1 && newL.length() <= 12)
			{
				if(newL.matches("[A-Za-z]*"))
				{
					System.out.println("Good last name: " + newL + "\n");
					this.loggedIn.setLastName(newL);
					valid = true;
				}else
				{
					System.out.println("\nIllegal characters\n");
				}//if-else
			}else
			{
				System.out.println("\nBad length\n");
			}//if-else
		}//while
	}//changeLastName
}
