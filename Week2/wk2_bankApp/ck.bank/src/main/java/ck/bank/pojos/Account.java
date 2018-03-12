package ck.bank.pojos;

public class Account {
	private int accountNumber;
	private int userId;
	private double balance;
	
	public Account() {}
	
	public Account(int a, int u, double b)
	{
		super();
		this.accountNumber = a;
		this.userId = u;
		this.balance = b;
	}
	
	public Account(int a)
	{
		super();
		this.accountNumber = a;
	}
	
	//==========================================================
	
	public int getAccountNumber()
	{
		return accountNumber;
	}
	public void setAccountNumber(int accountId)
	{
		this.accountNumber = accountId;
	}
	
	//============================================================
	
	public int getUserId()
	{
		return userId;
	}
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	
	//===========================================================
	
	public double getBalance()
	{
		return balance;
	}
	public void setBalance(double balance)
	{
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account #: " + accountNumber + " | Balance: $" + balance;
	}
	
	
	
	
}//Account
