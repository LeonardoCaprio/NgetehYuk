import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import order.Drink;
import order.Milktea;
import order.Tea;
import order.Transaction;

public class Database {
	private Connect connect = Connect.getConnection();
	
	public void getDrink(ArrayList<Drink> listDrink) {
		listDrink.clear();
		
		String queryMilkTea = "SELECT * FROM milktea";
		String queryTea = "SELECT * FROM tea";
		
		ResultSet rsMilkTea = connect.executeQuery(queryMilkTea);
		try {
			while(rsMilkTea.next()) {
				String id = rsMilkTea.getString("DrinkID");
				String name = rsMilkTea.getString("DrinkName");
				int price = rsMilkTea.getInt("DrinkPrice");
				String milkType = rsMilkTea.getString("MilkType");
				
				listDrink.add(new Milktea(id, name, price, milkType));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rsTea = connect.executeQuery(queryTea);
		try {
			while(rsTea.next()) {
				String id = rsTea.getString("DrinkID");
				String name = rsTea.getString("DrinkName");
				int price = rsTea.getInt("DrinkPrice");
				String sugarType = rsTea.getString("SugarType");
				
				listDrink.add(new Tea(id, name, price, sugarType));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertTransaction(String trId, String drinkId, String name, int quantity) {
		String query = String.format("INSERT INTO transaction VALUES ('%s', '%s', '%s', '%d')", trId, drinkId, name, quantity);
		connect.executeUpdate(query);
	}
	
	public String generateId() {
		String query = "SELECT * FROM transaction";
		
		ResultSet rs = connect.executeQuery(query);
		String lastId = "";
		try {
			while(rs.next()) {
				if(rs.isLast()) { 
					lastId = rs.getString("TransactionID");
				}
			}
			if(lastId.isEmpty()) {
				return "TR001";
			}
			
			String idNumber = lastId.substring(2,5); 
			int idNum = Integer.parseInt(idNumber);
			idNum++; 
			
			if(idNum < 10) {
				return "TR00" + idNum;
			}else if (idNum < 100) {
				return "TR0" + idNum;
			}else {
				return "TR" + idNum;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void getTrasaction(ArrayList<Transaction> listTransaction) {
		listTransaction.clear();
		String query = "SELECT * FROM transaction";
		
		ResultSet rs = connect.executeQuery(query);
		try {
			while(rs.next()) {
				String Trid = rs.getString("TransactionID");
				String DrinkId = rs.getString("DrinkID");
				String name = rs.getString("BuyerName");
				int qty = rs.getInt("Quantity");
				
				listTransaction.add(new Transaction(Trid, DrinkId, name, qty));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteTransaction(String deletedId) {
		String query = String.format("DELETE FROM Transaction WHERE TransactionID = '%s'", deletedId);
		connect.executeUpdate(query);
	}
}
