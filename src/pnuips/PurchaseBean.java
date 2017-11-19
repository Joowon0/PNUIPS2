package pnuips;

import java.sql.*;

public class PurchaseBean {
	private String buyerID;
	private int itemID;
	private int sellerID;
	private int amount;
	private int money;
	private Timestamp saletime;
	private Boolean coupon10;
	private Boolean coupon30;
	
	
	public void initiate (String buyerID, int itemID, int sellerID, int amount, int money,
			Timestamp saletime, Boolean coupon10, Boolean coupon30) {
		this.buyerID = buyerID;
		this.itemID = itemID;
		this.sellerID = sellerID;
		this.amount = amount;
		this.money = money;
		this.saletime = saletime;
		this.coupon10 = coupon10;
		this.coupon30 = coupon30;
	}
	
	public void xprint() {
		System.out.println("Buyer ID  : " + buyerID);
		System.out.println("Item ID   : " + itemID);
		System.out.println("Seller ID : " + sellerID);
		System.out.println("Amount    : " + amount);
		System.out.println("Money     : ￦" + money);
		System.out.println("Sold Time : " + saletime);
		System.out.println("coupon10 : " + coupon10);
		System.out.println("coupon30 : " + coupon30);
	}
	public String yprint() {
		String out = "<br>Buyer ID  : " + buyerID
				+ "<br>Item ID   : " + itemID
				+ "<br>Seller ID : " + sellerID
				+ "<br>Amount    : " + amount
				+ "<br>Money     : ￦" + money
				+ "<br>Sold Time : " + saletime
				+ "<br>coupon10 : " + coupon10
				+ "<br>coupon30 : " + coupon30 + "<br>";
		return out;
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
	public int getMoney() {
		return money;
	}
	public Timestamp getSaleTime() {
		return saletime;
	}
	public Boolean getCoupon10() {
		return coupon10;
	}
	public Boolean getCoupon30() {
		return coupon30;
	}
}
