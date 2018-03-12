package ck.bankApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import ck.bankApp.User;

public class UserIO {
	final static String filename = "./accounts.txt";
	
	/*
	//DONT USE THIS FUNCTION FOR WRITING A SINGLE USER
	public void writeUser(User aUser)
	{
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true)))
		//try(FileWriter bw = new FileWriter(filename,true))
		{
			bw.write(aUser.toString()+"\n");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}//write user
	*/
	
	//===============================================
	
	public void writeAllUsers(List<User> users)
	{
		//delete old version of file
		File fold = new File(filename);
		fold.delete();
		//create new blank file with the same name
		File fnew = new File(filename);
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename,true)))
		{
			for(User u : users)
			{
				bw.write(u.toString());
			}
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
		}//try-catch block
	}//writeAllUsers
	
	//===================================================
	
	public List<User> readAllUsers(){
		List<User> users = new ArrayList<User>();
		try(BufferedReader br = new BufferedReader(new FileReader(filename)))
		{
			String line = null;
			//read lines from the file until a blank line is encountered
			while((line=br.readLine()) != null)
			{
				String[] data = line.split(":");
				User temp = new User();
				//parse the data fields and add them to a User instance, add that to the list of Users
				temp.setUsername(data[0]);
				temp.setPassHash(data[1]);
				temp.setFirstName(data[2]);
				temp.setLastName(data[3]);
				temp.setBalance(Double.parseDouble(data[4]));
				
				users.add(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}//read users
}
