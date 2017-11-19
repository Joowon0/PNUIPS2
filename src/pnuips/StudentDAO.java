package pnuips;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	
	public static int insertStudent(StudentBean vo) {
		int status = 0;
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pst = conn.prepareStatement(
					"INSERT INTO Student VALUES(?, ?, ?);");
			
			pst.setInt(1, vo.getId());
			pst.setString(2, vo.getName());
			pst.setFloat(3, vo.getScore());
			
			status = pst.executeUpdate();
			
			pst.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static StudentBean selectStudent(int id) {
	    StudentBean student = null;
	    try {
                Connection conn = ConnectionProvider.getConnection();
                Statement st = conn.createStatement();
                
                ResultSet rs = st.executeQuery("SELECT * FROM Student WHERE ID = " + id);
                while(rs.next()) {
                    student = new StudentBean();
                    
                    int sId = rs.getInt("id");
                    String sName = rs.getString("name");
                    float sScore = rs.getFloat("score");
                    
                    System.out.println(sId + " " + sName + " " + sScore);
                    
                    student.setId(sId);
                    student.setName(sName);
                    student.setScore(sScore);
                }
                
                st.close();
                conn.close();
                
                return student;
            } catch(Exception e) {
                    e.printStackTrace();
            }
	    return null;
	}
	
	public static List<StudentBean> selectAllStudent() {
            List<StudentBean> student = new ArrayList<StudentBean>();
            try {
                Connection conn = ConnectionProvider.getConnection();
                Statement st = conn.createStatement();
                
                ResultSet rs = st.executeQuery("SELECT * FROM Student");
                while(rs.next()) {
                    StudentBean s = new StudentBean();
                    
                    int sId = rs.getInt("id");
                    String sName = rs.getString("name");
                    float sScore = rs.getFloat("score");
                    
                    s.setId(sId);
                    s.setName(sName);
                    s.setScore(sScore);
                    
                    student.add(s);
                }
                
                st.close();
                conn.close();
            } catch(Exception e) {
                    e.printStackTrace();
            }
            return student;
        }
	
	public static final void main(String[] args) {
	    StudentDAO dao = new StudentDAO();
	    List<StudentBean> daos = dao.selectAllStudent();
	    
	    for(int i=0;i<daos.size(); i++)
	    	System.out.println(daos.get(i).getName());
	}
}
