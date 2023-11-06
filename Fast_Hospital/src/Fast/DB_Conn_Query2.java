package Fast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DB_Conn_Query2 {
	Connection con = null;
	private PreparedStatement pstmt; //Query 작성 객체
	private ResultSet rs; //Query 결과 커서
	String url = "jdbc:oracle:thin:@hxlab.co.kr:51521:XE"; 
	String id = "RESERVATION"; String password = "1234";

	public DB_Conn_Query2() {
	try { Class.forName("oracle.jdbc.driver.OracleDriver");
	System.out.println("드라이버 적재 성공");
	} catch (ClassNotFoundException e) { System.out.println(e); }
	}

	public Connection DB_Connect() {
		try { con = DriverManager.getConnection(url, id, password);
		System.out.println("DB 연결 성공");
		} catch (SQLException e) { System.out.println("Connection Fail"); }
		return con;
		}
	/*public void 환자_login() {
		System.out.println("ddddd");
		if( con == null ) return;
		try {
	    	 Statement stmt = con.createStatement();
	    	 ResultSet rs = stmt.executeQuery("select 환자이름 from 환자" );
	    	 while (rs.next()) {
	    	        String 환자이름 = rs.getString("환자이름");
	    	        System.out.println(환자이름);
	    	      }
	    	 rs.close();
	    	 stmt.close();
		} catch(SQLException e) {
	    	 System.out.println(e.getMessage());
	     } finally {
	    	 try {
	    		 con.close();
	    	 } catch(SQLException e) { }
	     }
	}*/
	
}
