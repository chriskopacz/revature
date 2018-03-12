package ck.bank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ck.bank.pojos.User;
import ck.bank.util.ConnectionFactory;



public class UserDaoImpl implements UserDao{
	public List<User> getAllUsers()
	{
		List<User> users = new ArrayList<User>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			String sql = "SELECT * FROM customer";										//statement
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{	
				User temp = new User();

				temp.setUserId(rs.getInt(1));			//user id
				temp.setUsername(rs.getString(2));		//username
				temp.setFirstName(rs.getString(3));		//first name
				temp.setLastName(rs.getString(4));		//last name
				temp.setPassHash(rs.getString(5));		//password hash
				users.add(temp);
				
			}//while
			
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}//try-catch
		
		return users;
	}//getAllUsers
	
	//================================================================================
	
	public int addUser(User newUser)
	{
		int value=-1;	//default return value
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			String sql = "{CALL create_new_user(?,?,?,?)}";										//callable statement
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setString(1,newUser.getUsername());
			cstmt.setString(2,newUser.getFirstName());
			cstmt.setString(3,newUser.getLastName());
			cstmt.setString(4,newUser.getPassHash());
			value = cstmt.executeUpdate();
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return value;
	}//addUser
	
	//================================================================================
	
	public User getUserByUsername(String name)
	{
		User result = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			
			String sql = "SELECT * FROM customer WHERE username = ?";							//prepared statement
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,name);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next())
			{
				result.setUsername(rs.getString(1));
				result.setFirstName(rs.getString(2));
				result.setLastName(rs.getString(3));
				result.setPassHash(rs.getString(4));
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	//================================================================================
	
	public int updateUsername(String newU,String old)
	{
		int value = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			
			String sql = "UPDATE customer SET username=? WHERE username=?";						//prepared statement
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,newU);
			pstmt.setString(2,old);
			value = pstmt.executeUpdate();
			
			if(value!=-1)
			{
				sql = "COMMIT";																	//statement
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return value;
	}//updateUsername

	//=============================================================================
	
	public int updateFirstName(String newF,String uname)
	{
		int value = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			
			String sql = "UPDATE customer SET firstname=? WHERE username=?";					//prepared statement
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,newF);
			pstmt.setString(2,uname);
			value = pstmt.executeUpdate();
			
			if(value!=-1)
			{
				sql = "COMMIT";																	//statement
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return value;
	}//updateUsername

	//=============================================================================

	public int updateLastName(String newL,String uname)
	{
		int value = -1;

		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{

			String sql = "UPDATE customer SET lastname=? WHERE username=?";					//prepared statement
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,newL);
			pstmt.setString(2,uname);
			value = pstmt.executeUpdate();

			if(value!=-1)
			{
				sql = "COMMIT";																//statement
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return value;
	}//updateUsername
	
	
	
	

	public int removeUserByUsername(String name) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
