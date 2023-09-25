package jsoft.object;

public class AccountObject extends CustomerObject{
	private int account_id;
	private int account_customer_id; 
	private int account_money; 
	private String account_actived_date; 
	private String account_notes; 
	private int account_current_money; 
	private int account_type;
	
	public AccountObject() {
		
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public int getAccount_customer_id() {
		return account_customer_id;
	}

	public void setAccount_customer_id(int account_customer_id) {
		this.account_customer_id = account_customer_id;
	}

	public int getAccount_money() {
		return account_money;
	}

	public void setAccount_money(int account_money) {
		this.account_money = account_money;
	}

	public String getAccount_actived_date() {
		return account_actived_date;
	}

	public void setAccount_actived_date(String account_actived_date) {
		this.account_actived_date = account_actived_date;
	}

	public String getAccount_notes() {
		return account_notes;
	}

	public void setAccount_notes(String account_notes) {
		this.account_notes = account_notes;
	}

	public int getAccount_current_money() {
		return account_current_money;
	}

	public void setAccount_current_money(int account_current_money) {
		this.account_current_money = account_current_money;
	}

	public int getAccount_type() {
		return account_type;
	}

	public void setAccount_type(int account_type) {
		this.account_type = account_type;
	}
	
	
}
