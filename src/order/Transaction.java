package order;

public class Transaction {
	private String trId;
	private String drinkId;
	private String name;
	private int qty;
	
	public Transaction(String trId, String drinkId, String name, int qty) {
		super();
		this.trId = trId;
		this.drinkId = drinkId;
		this.name = name;
		this.qty = qty;
	}

	public String getTrId() {
		return trId;
	}

	public void setTrId(String trId) {
		this.trId = trId;
	}

	public String getDrinkId() {
		return drinkId;
	}

	public void setDrinkId(String drinkId) {
		this.drinkId = drinkId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
}
