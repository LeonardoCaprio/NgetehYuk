package order;

public class Tea extends Drink {

	private String sugarType;
	
	public Tea(String id, String name, int price, String sugarType) {
		super(id, name, price);
		this.sugarType = sugarType;
		// TODO Auto-generated constructor stub	
	}

	public String getSugarType() {
		return sugarType;
	}

	public void setSugarType(String sugarType) {
		this.sugarType = sugarType;
	}

	@Override
	public void printAttribute() {
			System.out.printf("| %-10s | %-15s | %-10s | %-15d | %-15s | %-18s |\n", id, name, "Tea", price, sugarType, "-");
	}
}
