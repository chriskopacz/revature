package ck.bank.dao;

import java.util.List;

import ck.bank.pojos.User;

public interface UserDao {
	public List<User> getAllUsers();
	public User getUserByUsername(String name);
	public int addUser(User newUser);
	public int removeUserByUsername(String name);
	public int updateUsername(String newU,String old);
	public int updateFirstName(String newF,String uname);
	public int updateLastName(String newL, String uname);
}
