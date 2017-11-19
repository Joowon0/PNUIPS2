package pnuips;

public class SellerBean {
	private int sellerID;
	private String sellerName;
	
	public void initiate (int sellerID, String sellerName) {
		this.sellerID = sellerID;
		this.sellerName = sellerName;
	}
	public void xprint() {
		System.out.println("Seller ID   : " + sellerID);
		System.out.println("Seller Name : " + sellerName);
	}
	
	public int getSellerID() {
		return sellerID;
	}
	public String getSellerName() {
		return sellerName;
	}
}
