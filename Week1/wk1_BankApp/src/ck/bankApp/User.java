package ck.bankApp;

import java.util.Arrays;

public class User {
	private String username;
	private String passHash;
	private String firstName;
	private String lastName;
	private double balance;
	
	public User() {}
	
	public User(String uName,String fName,String lName,double bal)
	{
		username = uName;
		passHash = null;
		firstName = fName;
		lastName = lName;
		balance = bal;
	}
	//===========================
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String uName)
	{
		this.username = uName;
	}
	//===========================
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String fName)
	{
		this.firstName = fName;
	}
	//===========================
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lName)
	{
		this.lastName = lName;
	}
	//===========================
	public double getBalance()
	{
		return balance;
	}
	public void setBalance(double newBalance)
	{
		this.balance = newBalance;
	}
	//===========================
	public String getPassHash()
	{
		return passHash;
	}
	public void setPassHash(String newPH)
	{
		this.passHash = newPH;
	}
	//===========================

	@Override
	public String toString() {
		return username + ":" + passHash + ":" + firstName
				+ ":" + lastName + ":" + balance;
	}
	
	
//	@Override
//	public String toString()
//	{
//		return new String(username + ":" + firstName + ":" + lastName + ":" + balance + "\n");
//	}
	
	
}
