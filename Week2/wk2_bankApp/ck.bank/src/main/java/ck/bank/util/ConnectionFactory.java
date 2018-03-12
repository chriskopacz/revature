package ck.bank.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private static ConnectionFactory cf = null;
	private static Boolean build = true;
	
	private ConnectionFactory()
	{
		build = false;
	}
	
	public static synchronized ConnectionFactory getInstance()
	{
		if(build == true)
		{
			cf = new ConnectionFactory();
		}
		return cf;
	}
	
	public Connection getConnection()
	{
		Connection conn = null;
		Properties prop = new Properties();
		//application.properties will go into src/main/resources
		
		try {
			prop.load(new FileReader("C:\\Users\\Chris Kopacz\\Documents\\GitHub\\BatchRepository\\Week2\\wk2_bankApp\\ck.bank\\src\\main\\java\\resources\\application.properties"));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("usr"),prop.getProperty("pwd"));
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return conn;
	}
}
