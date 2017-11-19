package pnuips;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
	public static int insertItem(ItemBean vo) {
		int status = 0;
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pst = conn.prepareStatement(
					"INSERT INTO item(itemid, itemname, price, brand, sellerid, stock)"
					+ "VALUES( ?, ?, ?, ?, ?, ?);");
			
			pst.setInt(1, vo.getItemID());
			pst.setInt(2, vo.getSellerID());
			pst.setString(3, vo.getItemName());
			pst.setInt(4, vo.getPrice());
			pst.setString(4, vo.getBrand());
			pst.setInt(4, vo.getStock());
			
			status = pst.executeUpdate();
			
			pst.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static ItemBean selectItem(int itemID, int sellerID) {
	    ItemBean item = null;
	    try {
                Connection conn = ConnectionProvider.getConnection();
                Statement st = conn.createStatement();
                
                ResultSet rs = st.executeQuery("SELECT *, price::numeric AS priceNUM FROM item "
                		+ "WHERE itemID = " + itemID + " AND sellerID = " + sellerID + ";");
                
                while(rs.next()) {
                    item = new ItemBean();
                    
                    String sItemName = rs.getString("itemName");
                    int sPrice = rs.getInt("priceNUM");
                    String sBrand = rs.getString("brand");
                    int sStock = rs.getInt("stock");
                    int sSales = rs.getInt("sales");
                    
                    item.initiate( itemID, sellerID, sItemName, sPrice, sBrand, sStock, sSales);
                    item.xprint();
                }
                
                st.close();
                conn.close();
                
                return item;
            } catch(Exception e) {
                    e.printStackTrace();
            }
	    return null;
	}
	
	public static String selectAllItem(String buyerID) {
            String item = "";
            try {
                Connection conn = ConnectionProvider.getConnection();
                Statement st = conn.createStatement();
                
                ResultSet rs = st.executeQuery("SELECT *, price::numeric AS priceNUM  FROM item "
                		+ "ORDER By itemID, SellerID");
                while(rs.next()) {
                    ItemBean s = new ItemBean();
                    
                    int sItemID = rs.getInt("itemID");
                    int sSellerID = rs.getInt("sellerID");
                    String sItemName = rs.getString("itemName");
                    int sPrice = rs.getInt("priceNUM");
                    String sBrand = rs.getString("brand");
                    int sStock = rs.getInt("stock");
                    int sSales = rs.getInt("sales");
                    
                                        
                    item += "<form name=\"fb\" method=\"get\" action=\"./MadeCartTemp.jsp\">"
	                			+ "<input type=\"hidden\" name=\"buyerID\" value=\"" + buyerID + "\">"
	                			+ "<input type=\"hidden\" name=\"itemID\" value=\"" + sItemID + "\">"
	                			+ "<input type=\"hidden\" name=\"sellerID\" value=\"" + sSellerID + "\">"
	                		
	                    		+ "<br>Item ID   : " + sItemID
	                    		+ "<br>Seller ID : " + sSellerID
	                    		+ "<br>Item Name : " + sItemName
	                    		+ "<br>Price     : " + sPrice
	                    		+ "<br>Brand     : " + sBrand
	                    		+ "<br>Stock     : " + sStock
	                    		+ "<br>Sales     : " + sSales
	                    		+ "<br>Number : "
	                    		+ "<input type=\"number\" name=\"Amount_select\" value=1 min= 1 max="+sStock + "><br>"
	                    		+ "<input type=\"submit\" value=\"CART\"/>"
                    		+ "</form>";
                }
                
                st.close();
                conn.close();
            } catch(Exception e) {
                    e.printStackTrace();
            }
            return item;
        }
	
	public static String selectAllItem(String buyerID,int itemID) {
        String item = "";
        try {
            Connection conn = ConnectionProvider.getConnection();
            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT *, price::numeric AS priceNUM  FROM item "
            		+ "WHERE itemID = " + itemID
            		+ "ORDER By itemID, SellerID");
            while(rs.next()) {
                ItemBean s = new ItemBean();
                
                int sItemID = rs.getInt("itemID");
                int sSellerID = rs.getInt("sellerID");
                String sItemName = rs.getString("itemName");
                int sPrice = rs.getInt("priceNUM");
                String sBrand = rs.getString("brand");
                int sStock = rs.getInt("stock");
                int sSales = rs.getInt("sales");
                
                                    
                item += "<form name=\"fb\" method=\"get\" action=\"./MadeCartTemp.jsp\">"
                			+ "<input type=\"hidden\" name=\"buyerID\" value=\"" + buyerID + "\">"
                			+ "<input type=\"hidden\" name=\"itemID\" value=\"" + sItemID + "\">"
                			+ "<input type=\"hidden\" name=\"sellerID\" value=\"" + sSellerID + "\">"
                		
                    		+ "<br>Item ID   : " + sItemID
                    		+ "<br>Seller ID : " + sSellerID
                    		+ "<br>Item Name : " + sItemName
                    		+ "<br>Price     : " + sPrice
                    		+ "<br>Brand     : " + sBrand
                    		+ "<br>Stock     : " + sStock
                    		+ "<br>Sales     : " + sSales
                    		+ "<br>Number : "
                    		+ "<input type=\"number\" name=\"Amount_select\" value=1 min= 1 max="+sStock + "><br>"
                    		+ "<input type=\"submit\" value=\"CART\"/>"
                		+ "</form>";
            }
            
            st.close();
            conn.close();
        } catch(Exception e) {
                e.printStackTrace();
        }
        return item;
    }
	
	
	public static final void main(String[] args) {
		//System.out.println(selectAllItem());
		/*
	    ItemDAO dao = new ItemDAO();
	    List<ItemBean> daos = dao.selectAllItem();
	    
	    for(int i=0;i<daos.size(); i++) {
	    	daos.get(i).xprint();
	    	System.out.println("");
	    }*/
	}
}
