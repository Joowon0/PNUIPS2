package pnuips;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Functions {
	// a. checking each client’s purchase history
	public static List<PurchaseBean> function_A(String userID) {
		List<PurchaseBean> purchaseL = new ArrayList<PurchaseBean>();
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(
            		"SELECT *, payment::numeric AS paymentNUM FROM purchase	WHERE buyerID = '" + userID + "' ;");
			
			
            while(rs.next()) {
                PurchaseBean purchase = new PurchaseBean();
                
                int sItemID = rs.getInt("itemID");
                int sSellerID = rs.getInt("sellerID");
                int sAmount = rs.getInt("amount");
                int sPayment = rs.getInt("paymentNUM");
                Timestamp sSaleTime = rs.getTimestamp("saletime");
                Boolean sCoupon10 = rs.getBoolean("coupon10");
                Boolean sCoupon30 = rs.getBoolean("coupon30");
                
                purchase.initiate(userID, sItemID, sSellerID, sAmount, sPayment, sSaleTime, sCoupon10, sCoupon30 );
                
                purchaseL.add(purchase);
            }
			
			st.close();
			conn.close();
			
			return purchaseL;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// b. checking each seller’s sale history
	public static String function_B(int sellerID) {
		String history = "";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(
            		"SELECT buyerid, itemID, sellerID, sellername, saleTime, coupon10, coupon30, amount, payment::numeric AS payed"
            		+ " FROM purchase NATURAL JOIN item NATURAL JOIN seller WHERE sellerID = " + sellerID + " ;");
			
			
            while(rs.next()) {                
                String sBuyerID = rs.getString("buyerID");
                int sItemID = rs.getInt("itemID");
                int sSellerID = rs.getInt("sellerID");
                String sSellerName = rs.getString("sellerName");
                int sAmount = rs.getInt("amount");
                int sPayment = rs.getInt("payed");
                //Timestamp sSaleTime = rs.getTimestamp("saleTime");
                Boolean sCoupon10 = rs.getBoolean("coupon10");
                Boolean sCoupon30 = rs.getBoolean("coupon30");
                
                history += "<br>Buyer ID  : " + sBuyerID
        				+ "<br>Item ID   : " + sItemID
        				+ "<br>Seller ID : " + sSellerID
        				+ "<br>Seller Name : " + sSellerName
        				+ "<br>Amount    : " + sAmount
        				+ "<br>Money     : ￦" + sPayment
        				//+ "<br>Sold Time : " + sSaleTime
        				+ "<br>coupon10 : " + sCoupon10
        				+ "<br>coupon30 : " + sCoupon30 + "<br>";
                System.out.println(history);
            }
			
			st.close();
			conn.close();
			
			return history;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// c. checking each seller’s number of sales
	public static String function_C() {
		String all_seller = "";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(
            		"SELECT sellerID, sellerName, COUNT(numOfSales) AS DEAL_COUNT, "
            		+ "CASE  WHEN SUM(numAmount) IS NULL THEN 0 ELSE sum(numAmount) END AS SOLD_COUNT "
            		+ "FROM item NATURAL JOIN seller "
            		+ "LEFT JOIN ( "
            		+ "SELECT sellerid AS numOfSales , itemID AS numItemID, amount AS numAmount "
            		+ "FROM purchase NATURAL JOIN item) t1 "
            		+ "ON item.sellerID = t1.numOfSales "
            		+ "AND item.itemID = t1.numItemId "
            		+ "GROUP BY sellerID, sellerName;");
			
            while(rs.next()) { 
	            int sSellerID = rs.getInt("sellerID");
	            String sSellerName = rs.getString("sellerName");
	            int sDeal = rs.getInt("DEAL_COUNT");
	            int sSold = rs.getInt("SOLD_COUNT");
	            
	            all_seller += "<br>Seller ID : " + sSellerID
	            		+ "<br>Seller Name : " + sSellerName
	            		+ "<br>Deal Count  : " + sDeal
	            		+ "<br>Sold Count  : " + sSold + "<br>";
            }
			st.close();
			conn.close();
			
			return all_seller;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// d. Finding top 3 best-selling items in a time interval (t1, t2) in terms of number of sales
	public static String function_D(String start, String end) {
		String seller = "";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(
            		"SELECT itemid, num_sales "
            		+ "FROM "
            		+ "((SELECT DISTINCT sum(amount) AS saleOfTop "
            		+ "FROM purchase "
            		+ "WHERE saletime BETWEEN '" + start + "' AND '" + end + "' "
            		+ "GROUP BY itemid "
            		+ "ORDER BY saleOfTop DESC LIMIT 3) t1 "
            		+ "JOIN "
            		+ "(SELECT itemid, sum(amount) AS num_sales "
            		+ "FROM purchase "
            		+ "WHERE saletime BETWEEN '" + start +"' AND '" + end + "' "
            		+ "GROUP BY itemid "
            		+ "ORDER BY num_sales DESC) t2 "
            		+ "ON (saleOfTop = num_sales)) t3;");
            
			int number = 0;
			int count = 0;
            while(rs.next()) { 
            	int sItemID = rs.getInt("itemID");
            	int sSale = rs.getInt("num_sales");
            	
            	if (count == 0)
            		number = sSale;
            	
            	if (sSale != number)
            		number = sSale;
	            if (sSale != number && count > 2) {
	            	break;
	            }
	            	
	            count++;
	            
	            seller += "<br>Item ID : " + sItemID
	            		+ "<br>Sale Num : " + sSale + "<br>";
            }
			st.close();
			conn.close();
			
			return seller;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// e. For a given seller, finding items that he/she does not sell 
	// but are among top 10 best-selling of highest-income
		public static String function_E(int sellerID) {
			String seller = "";
			try {
				Connection conn = ConnectionProvider.getConnection();
				Statement st = conn.createStatement();
	            
	            ResultSet rs = st.executeQuery(
	            		"SELECT * "
	            		+ "FROM ( "
	            		+ "SELECT itemID, itemName, sum(amount) AS totalSale, "
	            		+ " 	sum(payment)::numeric AS totalPayed "
	            		+ "FROM purchase NATURAL JOIN item "
	            		+ "GROUP BY itemID, itemName "
	            		+ "ORDER BY sum(payment) DESC LIMIT 10) t1 "
	            		+ "WHERE itemID NOT IN (SELECT itemID "
	            		+ "	FROM purchase NATURAL JOIN item "
	            		+ "	WHERE sellerID = " + sellerID
	            		+ "	GROUP BY itemID, sellerID, itemName) "
	            		+ "ORDER BY itemID;");
				
	            while(rs.next()) { 
	            	int sItemID = rs.getInt("itemID");
	            	String sItemName = rs.getString("itemName");
	            	int sSale = rs.getInt("totalSale");
	            	int sPayment = rs.getInt("totalPayed");
	            	  
		            seller += "<br>Item ID      : " + sItemID
		            		+ "<br>Item Name    : " + sItemName 
		            		+ "<br>Sales        : " + sSale
		            		+ "<br>Earned Money : ￦" + sPayment + "<br>";
	            }
				st.close();
				conn.close();
				
				return seller;
			} catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		public static String function_F() {
			String all_seller = "";
			try {
				Connection conn = ConnectionProvider.getConnection();
				Statement st = conn.createStatement();
	            
	            ResultSet rs = st.executeQuery(
	            		"SELECT itemID, sellerID, sellerName, itemname, sum(amount) AS cart, stock, brand "
	            		+ "FROM item NATURAL JOIN cart NATURAL JOIN seller "
	            		+ "GROUP BY itemID, sellerID, sellerName "
	            		+ "HAVING sum(amount) > stock;");
				
	            while(rs.next()) {
	            	int sItemID = rs.getInt("itemID");
	   	            int sSellerID = rs.getInt("sellerID");
		            String sSellerName = rs.getString("sellerName");
		            String sItemName = rs.getString("itemname");
		            int sCart = rs.getInt("cart");
		            int sStock = rs.getInt("stock");
		            String sBrand = rs.getString("brand");
		          		            
		            all_seller += "<br>Item ID : " + sItemID
		            		+ "<br>Seller ID : " + sSellerID
		            		+ "<br>Seller Name : " + sSellerName
		            		+ "<br>Item Name   : " + sItemName
		            		+ "<br>Cart        : " + sCart
		            		+ "<br>Stock       : " + sStock
		            		+ "<br>Brand       : " + sBrand + "<br>";
	            }
				st.close();
				conn.close();
				
				return all_seller;
			} catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		public static String function_G() {
			String all_seller = "";
			try {
				Connection conn = ConnectionProvider.getConnection();
				Statement st = conn.createStatement();
	            
	            ResultSet rs = st.executeQuery(
	            		"(SELECT itemID, sellerID, itemname, price::numeric AS priceNUM, brand, stock "
	            		+ "FROM client JOIN purchase ON userid = buyerid "
	            		+ " NATURAL JOIN item "
	            		+ "WHERE birth + interval '20 year' <= current_date "
	            		+ "  AND birth + interval '30 year' > current_date "
	            		+ "GROUP BY itemid, itemname, price, brand, sellerID, stock, sales "
	            		+ "ORDER BY sum(amount) DESC LIMIT 10) "
	            		+ "INTERSECT "
	            		+ "(SELECT itemID, sellerID, itemname, price::numeric AS priceNUM, brand, stock "
	            		+ "FROM client JOIN purchase ON userid = buyerid "
	            		+ "NATURAL JOIN item "
	            		+ "WHERE birth + interval '30 year' <= current_date "
	            		+ " AND birth + interval '40 year' > current_date "
	            		+ "GROUP BY itemid, itemname, price, brand, sellerID, stock, sales "
	            		+ "ORDER BY sum(amount) DESC LIMIT 10);" );
				
	            while(rs.next()) {
	            	int sItemID = rs.getInt("itemID");
	   	            int sSellerID = rs.getInt("sellerID");
		            String sItemName = rs.getString("itemname");
		            int sPrice = rs.getInt("priceNUM");
		            int sStock = rs.getInt("stock");
		            String sBrand = rs.getString("brand");
		          		            
		            all_seller += "<br>Item ID : " + sItemID
		            		+ "<br>Seller ID : " + sSellerID
		            		+ "<br>Item Name   : " + sItemName
		            		+ "<br>Price       : " + sPrice
		            		+ "<br>Stock       : " + sStock
		            		+ "<br>Brand       : " + sBrand + "<br>";
	            }
				st.close();
				conn.close();
				
				return all_seller;
			} catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	public static final void main(String[] args) {
		System.out.println(function_D("2016-12-10 00:00:00", "2016-12-12 00:00:00"));
	}
}
