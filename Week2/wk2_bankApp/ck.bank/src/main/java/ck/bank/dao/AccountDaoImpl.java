package ck.bank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ck.bank.pojos.Account;
import ck.bank.util.ConnectionFactory;

public class AccountDaoImpl implements AccountDao{

	public List<Account> getAllAccounts(int uid) 
	{
		List<Account> accounts = new ArrayList<Account>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			String sql = "SELECT * FROM account WHERE userid=?";										//prepared statement
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{	
				Account temp = new Account();

				temp.setAccountNumber(rs.getInt(1));		//accountid
				temp.setUserId(rs.getInt(2));			//userid
				temp.setBalance(rs.getDouble(3));		//balance
				accounts.add(temp);
				
			}//while
			
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}//try-catch
		
		return accounts;
	}//getAllAccounts
	
	//==============================================================
	
	public int createNewAccount(int uid, double newB)
	{
		int value = -1;
	
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			String sql = "{CALL create_new_account(?,?)}";										//callable statement
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setInt(1,uid);
			cstmt.setDouble(2,newB);
			value = cstmt.executeUpdate();
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return value;
	}//createNewAccount
	
	//===============================================================
	
	public int updateAccountBalance(int aNumber,double bal)
	{
		int value = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			
			String sql = "UPDATE account SET balance=? WHERE accountnumber=?";						//prepared statement
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1,bal);
			pstmt.setInt(2,aNumber);
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
		
	}//updateAccountBalance

}//AccountDaoImpl
