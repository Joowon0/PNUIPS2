package pnuips;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellerDAO {
	public static int insertCart(SellerBean vo) {
		int status = 0;
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pst = conn.prepareStatement(
					"INSERT INTO cart VALUES( ?, ?);");
			
			pst.setInt(1, vo.getSellerID());
			pst.setString(2, vo.getSellerName());
			
			status = pst.executeUpdate();
			
			pst.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static SellerBean selectSeller(int sellerID) {
	    SellerBean seller = null;
	    try {
                Connection conn = ConnectionProvider.getConnection();
                Statement st = conn.createStatement();
                
                ResultSet rs = st.executeQuery("SELECT * FROM seller WHERE sellerID = " + sellerID);
                
                while(rs.next()) {
                    seller = new SellerBean();
                    
                    String sSellerName = rs.getString("sellername");
                    
                    seller.initiate( sellerID, sSellerName);
                    
                    seller.xprint();
                }
                
                st.close();
                conn.close();
                
                return seller;
            } catch(Exception e) {
                    e.printStackTrace();
            }
	    return null;
	}
	
	public static List<SellerBean> selectAllSeller() {
            List<SellerBean> sellerL = new ArrayList<SellerBean>();
            try {
                Connection conn = ConnectionProvider.getConnection();
                Statement st = conn.createStatement();
                
                ResultSet rs = st.executeQuery("SELECT * FROM seller");
                while(rs.next()) {
                    SellerBean s = new SellerBean();
                    
                    int sSellerID = rs.getInt("sellerid");
                    String sSellerName = rs.getString("sellerName");
                  
                                        
                    s.initiate( sSellerID, sSellerName);
                    
                    sellerL.add(s);
                }
                
                st.close();
                conn.close();
            } catch(Exception e) {
                    e.printStackTrace();
            }
            return sellerL;
        }
	public static final void main(String[] args) {
	    SellerDAO dao = new SellerDAO();
	    List<SellerBean> daos = dao.selectAllSeller();
	    
	    for(int i=0;i<daos.size(); i++) {
	    	daos.get(i).xprint();
	    	System.out.println("");
	    }
	}
}
