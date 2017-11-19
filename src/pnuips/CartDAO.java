package pnuips;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
	public static int insertCart(CartBean vo) {
		int status = 0;
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pst = conn.prepareStatement(
					"INSERT INTO cart VALUES( ?, ?, ?, ?);");
			
			pst.setString(1, vo.getBuyerID());
			pst.setInt(2, vo.getItemID());
			pst.setInt(3, vo.getSellerID());
			pst.setInt(4, vo.getAmount());
			
			status = pst.executeUpdate();
			
			pst.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static CartBean selectCart(String buyerID, int itemID, int sellerID) {
	    CartBean cart = null;
	    try {
                Connection conn = ConnectionProvider.getConnection();
                Statement st = conn.createStatement();
                
                ResultSet rs = st.executeQuery("SELECT * FROM cart WHERE buyerID = '" + buyerID
                		+ "' AND itemID = " + itemID
                		+ " AND sellerID = " + sellerID);
                
                while(rs.next()) {
                    cart = new CartBean();
                    
                    int sAmount = rs.getInt("amount");
                    
                    cart.initiate(buyerID, itemID, sellerID, sAmount);
                    cart.xprint();
                }
                
                st.close();
                conn.close();
                
                return cart;
            } catch(Exception e) {
                    e.printStackTrace();
            }
	    return null;
	}
	
	// to find one's cart
	public static String showCart4Person(String buyerID) {
		String cart = "";
	    try {
                Connection conn = ConnectionProvider.getConnection();
                Statement st = conn.createStatement();
                
                ResultSet rs = st.executeQuery("SELECT * "
                		+ "FROM cart "
                		+ "WHERE buyerid = '" + buyerID + "' "
                	    + "ORDER By itemID, sellerID;");
                
                while(rs.next()) {
                    
                	int sItemID = rs.getInt("itemID");
                	int sSellerID = rs.getInt("sellerID");
                    int sAmount = rs.getInt("amount");
                    
                    cart += ""
                    	  + "<form name=\"fb\" method=\"get\" action=\"./Purchase.jsp\">"
                    			+ "<input type=\"hidden\" name=\"buyerID\" value=\"" + buyerID + "\">"
                    			+ "<input type=\"hidden\" name=\"itemID\" value=\"" + sItemID + "\">"
                    			+ "<input type=\"hidden\" name=\"sellerID\" value=\"" + sSellerID + "\">"
                    			+ "<br>Item ID   : " + sItemID
                    			+ "<br>Seller ID : " + sSellerID
                    			+ "<br>Amount    : " + sAmount + "<br>" 
                    			+ "<input type=\"submit\" value=\"BUY\"/>"
                    	  + "</form>";
                }
                
                st.close();
                conn.close();
                
                return cart;
            } catch(Exception e) {
                    e.printStackTrace();
            }
	    return null;
	}
	
	public static List<CartBean> selectAllCart() {
            List<CartBean> cartL = new ArrayList<CartBean>();
            try {
                Connection conn = ConnectionProvider.getConnection();
                Statement st = conn.createStatement();
                
                ResultSet rs = st.executeQuery("SELECT * FROM cart");
                while(rs.next()) {
                    CartBean s = new CartBean();
                    
                    String sBuyerID = rs.getString("buyerID");
                    int sItemID = rs.getInt("itemID");
                    int sSellerID = rs.getInt("sellerID");
                    int sAmount = rs.getInt("amount");
                                        
                    s.initiate(sBuyerID, sItemID, sSellerID, sAmount);
                    
                    cartL.add(s);
                }
                
                st.close();
                conn.close();
            } catch(Exception e) {
                    e.printStackTrace();
            }
            return cartL;
        }
	public static final void main(String[] args) {
	    CartDAO dao = new CartDAO();
	    List<CartBean> daos = dao.selectAllCart();
	    
	    for(int i=0;i<daos.size(); i++) {
	    	daos.get(i).xprint();
	    	System.out.println("");
	    }
	}

}
