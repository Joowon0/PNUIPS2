package pnuips;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO {
	public static int insertPurchase(PurchaseBean vo) {
		int status = 0;
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pst = conn.prepareStatement(
					"INSERT INTO purchase(buyerid, itemid, sellerid, amount, saletime, coupon10, coupon30)"
					+ " VALUES( ?, ?, ?, ?, ?, ?, ?);");
			
			pst.setString(1, vo.getBuyerID());
			pst.setInt(2, vo.getItemID());
			pst.setInt(3, vo.getSellerID());
			pst.setInt(4, vo.getAmount());
			pst.setTimestamp(5, vo.getSaleTime());
			pst.setBoolean(6, vo.getCoupon10());
			pst.setBoolean(7, vo.getCoupon30());
			
			
			status = pst.executeUpdate();
			
			pst.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static PurchaseBean selectParchase(String buyerID, int itemID, int sellerID, Timestamp saletime) {
	    PurchaseBean purchase = null;
	    try {
                Connection conn = ConnectionProvider.getConnection();
                Statement st = conn.createStatement();
                
                ResultSet rs = st.executeQuery("SELECT * FROM cart WHERE buyerID = " + buyerID
                		+ " AND itemID = " + itemID
                		+ " AND sellerID = " + sellerID
                		+ " AND saletime = " + saletime );
                
                while(rs.next()) {
                    purchase = new PurchaseBean();
                    
                    int sAmount = rs.getInt("amount");
                    int sPayment = rs.getInt("payment");
                    Boolean sCoupon10 = rs.getBoolean("coupon10");
                    Boolean sCoupon30 = rs.getBoolean("coupon30");
                    
                    purchase.initiate(buyerID, itemID, sellerID, sAmount, sPayment, saletime, sCoupon10, sCoupon30 );
                    purchase.xprint();
                }
                
                st.close();
                conn.close();
                
                return purchase;
            } catch(Exception e) {
                    e.printStackTrace();
            }
	    return null;
	}
	
	public static List<PurchaseBean> selectAllPurchase() {
            List<PurchaseBean> purchaseL = new ArrayList<PurchaseBean>();
            try {
                Connection conn = ConnectionProvider.getConnection();
                Statement st = conn.createStatement();
                
                ResultSet rs = st.executeQuery("SELECT *, payment::numeric AS payNum FROM purchase");
                while(rs.next()) {
                    PurchaseBean s = new PurchaseBean();
                    
                    String sBuyerID = rs.getString("buyerID");
                    int sItemID = rs.getInt("itemID");
                    int sSellerID = rs.getInt("sellerID");
                    int sAmount = rs.getInt("amount");
                    int sPayment = rs.getInt("payNum");
                    Timestamp sSaleTime = rs.getTimestamp("saleTime");
                    Boolean sCoupon10 = rs.getBoolean("coupon10");
                    Boolean sCoupon30 = rs.getBoolean("coupon30");
                                        
                    s.initiate(sBuyerID, sItemID, sSellerID, sAmount, sPayment, sSaleTime, sCoupon10, sCoupon30 );
                    
                    purchaseL.add(s);
                }
                
                st.close();
                conn.close();
            } catch(Exception e) {
                    e.printStackTrace();
            }
            return purchaseL;
        }
	// to find one's purchase
		public static String showPurchase4Person(String buyerID) {
			String purchase = "";
		    try {
	                Connection conn = ConnectionProvider.getConnection();
	                Statement st = conn.createStatement();
	                
	                ResultSet rs = st.executeQuery("SELECT *, payment::numeric AS payNum "
	                		+ "FROM purchase "
	                		+ "WHERE buyerid = '" + buyerID + "' "
	                	    + "ORDER By itemID, sellerID; ");
	                
	                while(rs.next()) {
	                    
	                	int sItemID = rs.getInt("itemID");
	                	int sSellerID = rs.getInt("sellerID");
	                    int sAmount = rs.getInt("amount");
	                    int sPayment = rs.getInt("payNum");
	                    Timestamp sSaleTime = rs.getTimestamp("saleTime");
	                    Boolean sCoupon10 = rs.getBoolean("coupon10");
	                    Boolean sCoupon30 = rs.getBoolean("coupon30");
	                    
	                    
	                    purchase += "<br>Item ID   : " + sItemID
	                    	  + "<br>Seller ID : " + sSellerID
	                    	  + "<br>Amount    : " + sAmount
	                    	  + "<br>Payment   : " + sPayment
	                    	  + "<br>Sale Time : " + sSaleTime
	                    	  + "<br>Coupon 10 : " + sCoupon10
	                    	  + "<br>Coupon 30 : " + sCoupon30 + "<br>";
	                }
	                
	                st.close();
	                conn.close();
	                
	                return purchase;
	            } catch(Exception e) {
	                    e.printStackTrace();
	            }
		    return null;
		}
	
	
	public static final void main(String[] args) {
	    /*PurchaseDAO dao = new PurchaseDAO();
	    List<PurchaseBean> daos = dao.selectAllPurchase();
	    
	    for(int i=0;i<daos.size(); i++) {
	    	daos.get(i).xprint();
	    	System.out.println("");
	    }*/
		System.out.println(showPurchase4Person("a@gmail.com"));
	}
}
