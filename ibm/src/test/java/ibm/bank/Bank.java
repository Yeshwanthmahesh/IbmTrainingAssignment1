package ibm.bank;

import java.sql.SQLException;

public class Bank {

	static java.util.Scanner in = new java.util.Scanner(System.in);

	static wallet.dao.AccountDao accountDao = new wallet.dao.AccountDaoImp();

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		int choice = 0;
		while (true) {
			System.out.print("1. Create Account\n");
			System.out.print("2. Get Account Balance\n");
			System.out.print("3. Deposit Amount\n");
			System.out.print("4. Withdraw Amount\n");
			System.out.print("5. Fund Transfer\n");
			System.out.print("0. Exit\n");
			choice = in.nextInt();
			switch (choice) {
			case 1:
				createAccount();
				break;
			case 2:
				getBalance();
				break;
			case 3:
				depositAmount();
				break;
			case 4:
				withdrawAmount();
				break;
			case 5:
				fundTransfer();
				break;
			case 0:
				System.exit(0);
			}
		}

	}

	private static void createAccount() {

		System.out.println("Account Name? ");
		in.nextLine();
		String accountName = in.nextLine();
		System.out.println(accountName);
		System.out.println("Account Pin? ");
		String accountPin = in.nextLine();
		System.out.println(accountPin);
		System.out.println("Phone Number? ");
		long phno = in.nextLong();
		System.out.println(phno);
		System.out.println("Account ID?");
		int accountId = in.nextInt();
		System.out.println(accountId);
		try {
			accountDao.createAccount(accountName, accountPin, accountId);
			System.out.println("Account Created with Account ID :" + accountId);

		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found Exception");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL Exception");
			e.printStackTrace();
		}
	}

	private static void getBalance() {
		System.out.println("Account ID?");
		int accountId = in.nextInt();
		try {
			accountDao.getBalance(accountId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void depositAmount() {

		System.out.println("Enter Account ID");
		int accountId = in.nextInt();
		System.out.println("Enter Amount");
		double amount = in.nextDouble();
		try {
			accountDao.depositAmount(accountId, amount);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void withdrawAmount() throws ClassNotFoundException, SQLException {
		System.out.println("Enter Account ID");
		int accountId = in.nextInt();
		System.out.println("Enter Account Pin");
		in.nextLine();
		String pin = in.nextLine();
		int pincheck = accountDao.pinCheck(accountId, pin);
		if (pincheck == 1) {
			System.out.println("Enter Amount");
			double amount = in.nextDouble();
			try {
				accountDao.withdrawAmount(accountId, amount);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Pin Incorrect");
		}
	}

	private static void fundTransfer() throws ClassNotFoundException, SQLException {

		System.out.println("Enter Account ID");
		int accountId = in.nextInt();
		System.out.println("Enter Account Pin");
		in.nextLine();
		String pin = in.nextLine();
		int pincheck = accountDao.pinCheck(accountId, pin);
		if (pincheck == 1) {
			System.out.println("Enter Amount");
			double amount = in.nextDouble();
			System.out.println("Enter the recepient account Id");
			int toAccountId = in.nextInt();

			accountDao.fundTransfer(accountId, toAccountId, amount);
		}
	}

}
