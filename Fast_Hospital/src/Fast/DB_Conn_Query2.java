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
	private PreparedStatement pstmt; //Query �ۼ� ��ü
	private ResultSet rs; //Query ��� Ŀ��
	String url = "jdbc:oracle:thin:@hxlab.co.kr:51521:XE"; 
	String id = "RESERVATION"; String password = "1234";

	public DB_Conn_Query2() {
	try { Class.forName("oracle.jdbc.driver.OracleDriver");
	System.out.println("����̹� ���� ����");
	} catch (ClassNotFoundException e) { System.out.println(e); }
	}

	public Connection DB_Connect() {
		try { con = DriverManager.getConnection(url, id, password);
		System.out.println("DB ���� ����");
		} catch (SQLException e) { System.out.println("Connection Fail"); }
		return con;
		}
	/*public void ȯ��_login() {
		System.out.println("ddddd");
		if( con == null ) return;
		try {
	    	 Statement stmt = con.createStatement();
	    	 ResultSet rs = stmt.executeQuery("select ȯ���̸� from ȯ��" );
	    	 while (rs.next()) {
	    	        String ȯ���̸� = rs.getString("ȯ���̸�");
	    	        System.out.println(ȯ���̸�);
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
