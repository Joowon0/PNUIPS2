package pnuips;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	
	private static String username = "postgres";
	private static String password = "dktTk";
	private static String url = "jdbc:postgresql://localhost:5432/PNUIPS";
	
	private static Connection conn = null;
	
	public static Connection getConnection() throws Exception {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, username, password);			} catch (Exception e) {
				// TODO Auto-generated catch block				e.printStackTrace();
		}
		return conn;
	}
}
