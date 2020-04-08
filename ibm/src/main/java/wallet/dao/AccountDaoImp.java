package wallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import wallet.util.DBUtil;

public class AccountDaoImp implements AccountDao {

	public int createAccount(String accountName, String pin, int accountId)
			throws ClassNotFoundException, SQLException {
		Connection connection = DBUtil.getConnection();

		String sql = "INSERT INTO ibmbank VALUES(?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, accountId);
		statement.setString(2, accountName);
		statement.setDouble(3, 0);
		statement.setString(4, pin);
		statement.executeUpdate();
		connection.close();

		return accountId;
	}

	public double getBalance(int accountId) throws ClassNotFoundException, SQLException {
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT account_Balance FROM ibmbank where Account_Id = ?";
		java.sql.PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, accountId);
		java.sql.ResultSet result = statement.executeQuery();
		if (result.next()) {
			double bal = result.getDouble("account_Balance");
			System.out.println("Account with ID: " + accountId + " has Balance " + bal);
		}

		return 0;
	}

	public double depositAmount(int accountId, double amount) throws ClassNotFoundException, SQLException {

		Connection connection = DBUtil.getConnection();
		String sql = "UPDATE ibmbank SET account_Balance = account_Balance+? WHERE Account_Id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setDouble(1, amount);
		statement.setInt(2, accountId);
		statement.executeUpdate();

		sql = "SELECT account_Balance FROM ibmbank where Account_Id = ?";
		statement = connection.prepareStatement(sql);
		statement.setInt(1, accountId);
		java.sql.ResultSet result = statement.executeQuery();
		if (result.next()) {
			double bal = result.getDouble("account_Balance");
			System.out.println("Amount deposited. Account with ID: " + accountId + " has Balance: " + bal);
		}
		connection.close();

		return 0;
	}

	public double withdrawAmount(int accountId, double amount) throws ClassNotFoundException, SQLException {
		Connection connection = DBUtil.getConnection();
		String sql = "UPDATE ibmbank SET account_Balance = account_Balance-? WHERE Account_Id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setDouble(1, amount);
		statement.setInt(2, accountId);
		statement.executeUpdate();

		sql = "SELECT account_Balance FROM ibmbank where Account_Id = ?";
		statement = connection.prepareStatement(sql);
		statement.setInt(1, accountId);
		java.sql.ResultSet result = statement.executeQuery();
		if (result.next()) {
			double bal = result.getDouble("account_Balance");
			System.out.println("Amount Withdrawn. Account with ID: " + accountId + " has Balance: " + bal);
		}
		connection.close();

		return 0;
	}

	public double fundTransfer(int accountId,int toAccountId, double amount) throws ClassNotFoundException, SQLException {

		Connection connection = DBUtil.getConnection();
		String sql = "UPDATE ibmbank SET account_Balance = account_Balance+? WHERE Account_Id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setDouble(1, amount);
		statement.setInt(2, toAccountId);
		statement.executeUpdate();
		
		sql = "UPDATE ibmbank SET account_Balance = account_Balance-? WHERE Account_Id = ?";
		statement = connection.prepareStatement(sql);
		statement.setDouble(1, amount);
		statement.setInt(2, accountId);
		statement.executeUpdate();
		
		sql = "SELECT account_Balance FROM ibmbank where Account_Id = ?";
		statement = connection.prepareStatement(sql);
		statement.setInt(1, accountId);
		java.sql.ResultSet result = statement.executeQuery();
		if (result.next()) {
			double bal = result.getDouble("account_Balance");
			System.out.println("Amount Transferred. Your Account Balance: " + bal);
		}
		connection.close();
		return 0;
	}

	public int pinCheck(int accountId, String pin) throws ClassNotFoundException, SQLException {

		int ch;
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT pin FROM ibmbank where Account_Id = ?";
		java.sql.PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, accountId);
		java.sql.ResultSet result = statement.executeQuery();
		if (result.next()) {
			String dbpin = result.getString("pin");
			if (dbpin.equalsIgnoreCase(pin)) {
				return 1;
			}
		}
		return 0;
	}

}
