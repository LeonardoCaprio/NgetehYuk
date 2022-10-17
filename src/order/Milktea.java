package order;

public class Milktea extends Drink{

	private String milkType;
	
	public Milktea(String id, String name, int price, String milkType) {
		super(id, name, price);
		this.milkType = milkType;
		// TODO Auto-generated constructor stub
	}

	public String getMilkType() {
		return milkType;
	}

	public void setMilkType(String milkType) {
		this.milkType = milkType;
	}

	@Override
	public void printAttribute() {
			System.out.printf("| %-10s | %-15s | %-10s | %-15d | %-15s | %-18s |\n", id, name, "Milk Tea", price, "-", milkType);
	}
}
