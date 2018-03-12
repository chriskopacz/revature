package ck.bank.dao;

import java.util.List;

import ck.bank.pojos.Account;

public interface AccountDao {
	public List<Account> getAllAccounts(int uid);
	public int createNewAccount(int uid,double newB);
	public int updateAccountBalance(int aNumber,double bal);
}
