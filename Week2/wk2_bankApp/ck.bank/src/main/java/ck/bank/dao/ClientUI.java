package ck.bank.dao;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import ck.bank.driver.Main;
import ck.bank.pojos.User;
import ck.bank.util.ConnectionFactory;

public class ClientUI {
	//public static Scanner sc = new Scanner(System.in);

	public int topUI()
	{
		int userInput = -1;
		boolean valid = false;

		while(!valid)
		{
			System.out.println("\nBANK INTERFACE");
			System.out.println("Make your selection:");
			System.out.println("1 - Create new user");
			System.out.println("2 - Login");
			System.out.println("3 - Exit");
			try
			{
				userInput = Main.sc.nextInt();
				if(userInput == 1 || userInput == 2 || userInput == 3)
				{
					System.out.println("\nGood selection: "+userInput + "\n");
					valid = true;
				}else
				{
					System.out.println("\nBad, need 1 | 2 | 3. Try again.\n");
					Main.sc.nextLine();
				}//if-else
			}catch(InputMismatchException ime)
			{
				System.out.println("\nNon-int input. Try again.\n");
				Main.sc.nextLine();
			}//try-catch
		}//while
		return userInput;
	}//topUI

	//=======================================================

	//public List<User> createNewUser(List<User> users)
	public void createNewUser()
	{
		User newPerson = new User();


		//validateUsername
		newPerson.setUsername(validateUsername());

		//validatePassword
		newPerson.setPassHash(validatePassword());

		//validateFirstName
		newPerson.setFirstName(validateFirstName());

		//validateLastName
		newPerson.setLastName(validateLastName());

		//validateBalance
		//newPerson.setBalance(validateBalance());

		//add new person to the list
		//users.add(newPerson);

		Main.uDao.addUser(newPerson);
		//return users;
	}//createNewUser

	//========================================================

	public String validateUsername()
	{
		boolean valid = false;
		boolean unique = true;
		List<String> usernames = new ArrayList<String>();
		String newU = "";
		
		//get list of taken usernames from the server
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			String sql = "SELECT username FROM customer";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				usernames.add(rs.getString(1));
			}//while
		} catch (SQLException e)
		{
			e.printStackTrace();
		}//try-catch

		
		//check that entered username meets form requirements and isnt already taken
		while(!valid)
		{
			System.out.println("Enter username: (1-10 characters, letters and #'s only)");
			newU = Main.sc.nextLine();
			unique = true;
			if(newU.length()>=1 && newU.length()<=10)
			{
				if(newU.matches("[A-Za-z0-9]*"))
				{
					for(String s : usernames)
					{
						if(s.equals(newU))
						{
							unique = false;
							break;
						}//if
					}//for
					if(!unique)
					{
						System.err.println("\nUsername taken\n");
					}else
					{
						System.out.println("Good username: " + newU + "\n");
						valid = true;
					}//if
				}else
				{
					System.out.println("\nIllegal characters\n");
				}//if
			}else
			{
				System.out.println("\nBad length\n");
			}//if

		}//while
		
		return newU;
	}//validateUsername

	//=================================================

	public String validateFirstName()
	{
		String newF = null;
		boolean valid = false;

		while(!valid)
		{
			System.out.println("Enter first name: (1-12 characters, letters only)");
			newF = Main.sc.nextLine();
			if(newF.length() >= 1 && newF.length() <= 10)
			{
				if(newF.matches("[A-Za-z]*"))
				{
					System.out.println("Good first name: " + newF +"\n");
					valid = true;
				}else
				{
					System.out.println("\nIllegal characters\n");
				}
			}else
			{
				System.out.println("\nBad length\n");
			}

		}//while
		return newF;
	}//validateFirstName

	//====================================================

	public String validateLastName()
	{
		String newL = null;
		boolean valid = false;

		while(!valid)
		{
			System.out.println("Enter last name: (1-12 characters, letters only)");
			newL = Main.sc.nextLine();
			if(newL.length() >= 1 && newL.length() <= 10)
			{
				if(newL.matches("[A-Za-z]*"))
				{
					System.out.println("Good last name: " + newL);
					valid = true;
				}else
				{
					System.out.println("\nIllegal characters\n");
				}
			}else
			{
				System.out.println("\nBad length\n");
			}

		}//while
		return newL;
	}//validateLastName

	//========================================================================

	public User userLogin()
	{
		String tempUname;
		String tempPass;
		byte[] bytesOfPass;
		byte[] passHash = null;
		StringBuilder temp = new StringBuilder();
		MessageDigest md;
		boolean valid = false;

		System.out.println("Please enter your username:");
		tempUname = Main.sc.next();

		List<User> users = Main.uDao.getAllUsers();
		
		for(User u : users)
		{
			if(tempUname.equals(u.getUsername()))
			{
				//check password if username is found
				System.out.println(tempUname + "\nEnter your password:");
				tempPass = Main.sc.next();
				bytesOfPass = tempPass.getBytes();

				try
				{
					md = MessageDigest.getInstance("MD5");
					passHash = md.digest(bytesOfPass);
					for(byte b : passHash)
					{
						temp.append(String.format("%02x", b & 0xff));
					}
					if(temp.toString().equals(u.getPassHash()))
					{
						System.out.println("Good login");
						return u;
					}else
					{
						System.out.println("Bad login");
						return null;
					}//if-else

				}catch(NoSuchAlgorithmException nsae)
				{
					System.out.println("MD5 Algorithm - login");
				}//try-catch

			}//if
		}//for


		//return null if username is not found in list of current users
		return null;
	}//userLogin

	//=========================================================================

	public String validatePassword()
	{
		String password;
		byte[] bytesOfPass;
		byte[] passHash = null;
		StringBuilder temp = new StringBuilder();
		MessageDigest md;
		boolean valid = false;

		while(!valid)
		{
			System.out.println("Enter password (8-20 characters)");
			password = Main.sc.nextLine();
			if(password.length() >= 8 && password.length() <= 20)
			{
				System.out.println("\n\nGood password");
				try
				{
					bytesOfPass = password.getBytes("UTF-8");

					try
					{
						md = MessageDigest.getInstance("MD5");
						passHash=md.digest(bytesOfPass);
						for(byte b : passHash)
						{
							temp.append(String.format("%02x", b & 0xff));
						}
					}catch(NoSuchAlgorithmException nsae)
					{
						nsae.printStackTrace();
						System.out.println("Broken in MD5 - validatePassword()");
					}//try-catch


				}catch(UnsupportedEncodingException uee)
				{
					uee.printStackTrace();
					System.out.println("Broken in getBytes() encoding - validatePassword()");
				}finally
				{
					valid = true;
				}

			}else
			{
				System.out.println("\nBad length\n");
			}//if-else

		}//while
		return temp.toString();
	}//validatePassword

}
