import java.util.ArrayList;
import java.util.Scanner;

import order.Drink;
import order.Milktea;
import order.Tea;
import order.Transaction;

public class Main {

	private Scanner scan = new Scanner(System.in);
	private ArrayList<Drink> listDrink = new ArrayList<>();
	private Database db = new Database();
	private ArrayList<Transaction> listTransaction = new ArrayList<>();
	
	public void cls() {
		for(int i = 0 ; i < 40 ; i++) {
			System.out.println();
		}
	}
	
	public void pressEnter() {
		System.out.println("Press Enter to Continue...");
		scan.nextLine();
		menu();
	}
	
	public void menu() {
		int opt;
		
		System.out.println("Ngeteh Yuk!");
		System.out.println("=============");
		System.out.println("1. Buy Tea");
		System.out.println("2. View Transaction");
		System.out.println("3. Delete Transaction");
		System.out.println("4. Exit");
		System.out.print(">> ");
		opt = scan.nextInt();
		scan.nextLine();
		
		switch(opt) {
		case 1: 
			cls();
			buyTea();
			break;
		case 2:
			cls();
			viewTransaction();
			break;
		case 3:
			cls();
			deleteTransaction();
			break;
		case 4:
			System.out.println("Thank you for choosing this program..!!");
			pressEnter();
			break;
		}
	}
	
	private void buyTea() {
		viewDrink();
		
		String id;
		int quantity = 0;
		String name = null;
		
		do {
			System.out.print("Input Drink's id want to buy: ");
			id = scan.nextLine();
		} while (!validateId(id));
		
		do {
			System.out.print("Input Quantity: ");
			quantity = scan.nextInt();
			scan.nextLine();
		} while (!(quantity > 0));
		
		do {
			System.out.print("Input Your Name [Must be 2 Words]: ");
			name = scan.nextLine();
		} while (!(name.contains(" ")));
		
		db.insertTransaction(db.generateId(), id, name, quantity);
		
		cls();
		if(id.contains("MT")) {
			int totalPrice = (getPriceDrink(id) * quantity) + 3500;
			System.out.println("===================================");
			System.out.println("Transcation Detail         ");
			System.out.println("===================================");
			System.out.printf("TransactionID: %s\n", db.generateId());
			System.out.printf("Buyer Name: %s\n", name);
			System.out.printf("Drink Name: %s\n", getDrinkName(id));
			System.out.printf("Drink Price: %d\n", getPriceDrink(id));
			System.out.println("Tax: 3500");
			System.out.printf("Total Price: %d\n", totalPrice);
			System.out.println("===================================");
		}
		if(id.contains("TE")) {
			int totalPrice = (getPriceDrink(id) * quantity) + 2000;
			System.out.println("===================================");
			System.out.println("Transcation Detail         ");
			System.out.println("===================================");
			System.out.printf("TransactionID: %s\n", db.generateId());
			System.out.printf("Buyer Name: %s\n", name);
			System.out.printf("Drink Name: %s\n", getDrinkName(id));
			System.out.printf("Drink Price: %d\n", getPriceDrink(id));
			System.out.println("Tax: 2000");
			System.out.printf("Total Price: %d\n", totalPrice);
			System.out.println("===================================");
		}
		pressEnter();
	}
	
	public int getPriceDrink(String id) {
		for(Drink d : listDrink) {
			if(d.getId().equals(id)) {
				return d.getPrice();
			}
		}
		return 0;
	}
	
	public String getDrinkName(String id) {
		for(Drink d : listDrink) {
			if(d.getId().equals(id)) {
				return d.getName();
			}
		}
		return null;
	}
	
	public boolean validateId(String id) {
		for(Drink d : listDrink) {
			if(d.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	public void viewDrink() {
		db.getDrink(listDrink);
		System.out.println("====================================================================================================");
		System.out.printf("| %-10s | %-15s | %-10s | %-15s | %-15s | %-18s |\n", "Drink ID", "Drink Name", "Drink Type", "Drink Price", "Sugar Type", "Milk Type");
		System.out.println("====================================================================================================");
		
		for(Drink d : listDrink) {
			if(d instanceof Tea) {
				d.printAttribute();
			}
		}
		for(Drink d : listDrink) {
			if(d instanceof Milktea) {
				d.printAttribute();
			}
		}
		System.out.println("====================================================================================================");
	}

	public void viewTr() {
		db.getTrasaction(listTransaction);
		System.out.println("============================================================");
		System.out.printf("| %-15s | %-10s | %-15s | %-10s |\n", "TransactionID", "DrinkID", "BuyerName", "Quantity");
		System.out.println("============================================================");
		for(Transaction t : listTransaction) {
			System.out.printf("| %-15s | %-10s | %-15s | %-10d |\n", t.getTrId(), t.getDrinkId(), t.getName(), t.getQty());
		}
		System.out.println("============================================================");
	}
	
	private void viewTransaction() {
		viewTr();
		pressEnter();
	}

	private void deleteTransaction() {
		viewTr();
		
		String deletedId;
		do {
			System.out.println("Input Transaction's Id want to deleted: ");
			deletedId = scan.nextLine();
		} while (!validateTrId(deletedId));
		db.deleteTransaction(deletedId);
		pressEnter();
	}
	
	public boolean validateTrId(String deletedId) {
		for(Transaction t : listTransaction) {
			if(t.getTrId().equals(deletedId)) {
				return true;
			}
		}
		return false;
	}

	public Main() {
		menu();
	}

	public static void main(String[] args) {
		new Main();

	}

}
