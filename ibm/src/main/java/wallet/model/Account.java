package wallet.model;

public class Account {

	private String accountId;
	private String accountName;
	private double accountBalance;
	private String pin;
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public Account(String accountId, String accountName, double accountBalance, String pin) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountBalance = accountBalance;
		this.pin = pin;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountName=" + accountName + ", accountBalance=" + accountBalance
				+ ", pin=" + pin + "]";
	}
	
	
}
