package pnuips;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
	public static int insertClient(ClientBean vo) {
		int status = 0;
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pst = conn.prepareStatement(
					"INSERT INTO client VALUES(?, ?, ?, ?, ?);");
			
			pst.setString(1, vo.getId());
			pst.setString(2, vo.getPassword());
			pst.setString(3, vo.getFirstName());
			pst.setString(4, vo.getLastName());
			pst.setString(5, vo.getBirth());
			
			status = pst.executeUpdate();
			
			pst.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static ClientBean selectClient(String id) {
	    ClientBean client = null;
	    try {
                Connection conn = ConnectionProvider.getConnection();
                Statement st = conn.createStatement();
                
                ResultSet rs = st.executeQuery("SELECT * FROM Client WHERE userID = '" + id+"'");
                while(rs.next()) {
                    client = new ClientBean();
                    
                    String sId = rs.getString("userID");
                    String sPassword = rs.getString("password");
                    String sFirstName = rs.getString("firstName");
                    String sLastName = rs.getString("familyName");
                    String sBirth = rs.getString("birth");
                    
                    Boolean sIsAdmin = rs.getBoolean("isAdmin");
                    Boolean sHasCoupon10 = rs.getBoolean("coupon10p");
                    Boolean sHasCoupon30 = rs.getBoolean("coupon30p");
                    
                    
                    client.initiate(sId, sPassword, sFirstName, sLastName, sBirth,
                    		sIsAdmin, sHasCoupon10, sHasCoupon30);
                    client.xprint();
                }
                
                st.close();
                conn.close();
                
                return client;
            } catch(Exception e) {
                    e.printStackTrace();
            }
	    return null;
	}
	
	public static List<ClientBean> selectAllClient() {
            List<ClientBean> clientL = new ArrayList<ClientBean>();
            try {
                Connection conn = ConnectionProvider.getConnection();
                Statement st = conn.createStatement();
                
                ResultSet rs = st.executeQuery("SELECT * FROM client");
                while(rs.next()) {
                    ClientBean s = new ClientBean();
                    
                    String sId = rs.getString("userID");
                    String sPassword = rs.getString("password");
                    String sFirstName = rs.getString("firstName");
                    String sLastName = rs.getString("familyName");
                    Date tempBirth = rs.getDate("birth");
                    
                    Boolean sIsAdmin = rs.getBoolean("isAdmin");
                    Boolean sHasCoupon10 = rs.getBoolean("coupon10p");
                    Boolean sHasCoupon30 = rs.getBoolean("coupon30p");
                    
                    DateFormat d = new SimpleDateFormat("YYYY/MM/dd");
                    String sBirth = d.format(tempBirth);
                    
                    s.initiate(sId, sPassword, sFirstName, sLastName, sBirth,
                    		sIsAdmin, sHasCoupon10, sHasCoupon30);
                    
                    clientL.add(s);
                }
                
                st.close();
                conn.close();
            } catch(Exception e) {
                    e.printStackTrace();
            }
            return clientL;
        }
	public static String showClass(String buyerID) {
		String seller = "";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(
            		"(SELECT payment::numeric, 'Regular' AS class "
            		+ "FROM client "
            		+ "WHERE userid = '" + buyerID + "' "
            		+ "AND payment::numeric < 200000) "
            		+ "UNION "
            		+ "(SELECT payment::numeric, 'VIP' AS class "
            		+ "FROM client "
            		+ "WHERE userid = '" + buyerID + "' "
            		+ "AND payment::numeric BETWEEN 200000 AND 499999) "
            		+ "UNION "
            		+ "(SELECT payment::numeric, 'VVIP' AS class "
            		+ "FROM client "
            		+ "WHERE userid = '" + buyerID + "' "
            		+ "  AND payment::numeric >= 500000);");
			
            while(rs.next()) { 
            	int sPayment = rs.getInt("payment");
            	String sClass = rs.getString("class");            	
	            
	            seller += "<br>Payment  : " + sPayment
	            		+ "<br>Class    : " + sClass + "<br>";
            }
			st.close();
			conn.close();
			
			return seller;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int totalPayment(String buyerID) {
		int total = 0;
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(
            		"SELECT payment::numeric AS Total_pay "
            		+ "FROM client "
            		+ "WHERE userid = '" + buyerID + "'; ");
            while(rs.next()) { 
            	total = rs.getInt("Total_pay");
            }
			
            
			st.close();
			conn.close();
			
			return total;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static final void main(String[] args) {

		System.out.println(totalPayment("a@gmail.com"));
	}
}
