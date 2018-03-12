package ck.bank.pojos;


import java.util.Arrays;

public class User {
	private String username;
	private String passHash;
	private String firstName;
	private String lastName;
	private int	userId;

	public User() {}

	public User(String uName,String fName,String lName)
	{
		super();
		username = uName;
		passHash = null;
		firstName = fName;
		lastName = lName;
		userId = 0;
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
	public int getUserId()
	{
		return userId;
	}
	public void setUserId(int newId)
	{
		this.userId = newId;
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
				+ ":" + lastName;
	}


	//		@Override
	//		public String toString()
	//		{
	//			return new String(username + ":" + firstName + ":" + lastName + ":" + balance + "\n");
	//		}


}

