package wallet.dao;

import java.sql.SQLException;

public interface AccountDao {

	public int createAccount(String accountName, String pin, int accountId) throws ClassNotFoundException, SQLException;
	
	public double getBalance(int accountId) throws ClassNotFoundException, SQLException;
	
	public double depositAmount(int accountId, double amount) throws ClassNotFoundException, SQLException;
	
	public double withdrawAmount(int accountId, double amount) throws ClassNotFoundException, SQLException;
	
	public double fundTransfer(int accountId, int toAccountId, double amount) throws ClassNotFoundException, SQLException;

	public int pinCheck(int accountId, String pin) throws ClassNotFoundException, SQLException;
}
