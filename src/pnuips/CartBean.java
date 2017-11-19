package pnuips;

public class CartBean {
	private String buyerID;
	private int itemID;
	private int sellerID;
	private int amount;
	
	public void initiate (String buyerID, int itemID, int sellerID, int amount ) {
		this.buyerID = buyerID;
		this.itemID = itemID;
		this.sellerID = sellerID;
		this.amount = amount;
	}
	public void xprint() {
		System.out.println("Buyer ID  : " + buyerID);
		System.out.println("Item ID   : " + itemID);
		System.out.println("Seller ID : " + sellerID);
		System.out.println("Amount    : " + amount);
	}
	
	public String getBuyerID() {
		return buyerID;
	}
	public int getItemID() {
		return itemID;
	}
	public int getSellerID() {
		return sellerID;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
