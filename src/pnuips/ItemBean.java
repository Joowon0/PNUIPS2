package pnuips;

public class ItemBean {
	private int itemID;
	private int sellerID;
	private String itemName;
	private int price;
	private String brand;
	private int stock;
	private int sales;
	
	
	public void initiate (int itemID, int sellerID, String itemName, int price, String brand, int stock, int sales) {
		this.itemID = itemID;
		this.sellerID = sellerID;
		this.itemName = itemName;
		this.price = price;
		this.brand = brand;
		this.stock = stock;
		this.sales = sales;
	}
	
	public void xprint() {
		System.out.println("Item ID   : " + itemID);
		System.out.println("Seller ID : " + sellerID);
		System.out.println("Item Name : " + itemName);
		System.out.println("Price     : " + price);
		System.out.println("Brand     : " + brand);
		System.out.println("Stock     : " + stock);
		System.out.println("Sales     : " + sales);
	}
	
	public int getItemID() {
		return itemID;
	}
	public int getSellerID() {
		return sellerID;
	}
	public String getItemName() {
		return itemName;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public void increStock(int stock) {
		this.stock += stock;
	}
	
	public int getSales() {
		return sales;
	}
}
